package com.example.mukgen.domain.rice.service;


import com.example.mukgen.domain.rice.controller.dto.request.MukgenPickRequest;
import com.example.mukgen.domain.rice.controller.dto.request.RiceRequest;
import com.example.mukgen.domain.rice.controller.dto.response.MukgenPickResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceTodayResponse;
import com.example.mukgen.domain.rice.entity.MukgenPick;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import com.example.mukgen.domain.rice.repository.MukgenPickRepository;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import com.example.mukgen.domain.rice.service.exception.MukgenPickNotFoundException;
import com.example.mukgen.domain.rice.service.exception.RiceNotFoundException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.service.exception.NoPermissionException;
import com.example.mukgen.infra.feign.client.NeisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@Transactional
@Service
public class RiceService {

    private final UserFacade userFacade;

    private final NeisUtil neisUtil;

    private final RiceRepository riceRepository;

    private final MukgenPickRepository mukgenPickRepository;

    private final ConcurrentHashMap<Integer, Rice> mealCache = new ConcurrentHashMap<>();

    public RiceResponse findRice(RiceRequest request){

        RiceType riceType = request.getRiceType();

        int year = request.getYear();

        int month = request.getMonth();

        int day = request.getDay();

        int addId = switch (riceType) {
            case BREAKFAST -> 1;
            case LUNCH -> 2;
            case DINNER -> 3;
        };

        int id = (year * 10000 + month * 100 + day) * 10 + addId;

        Rice rice = mealCache.get(id);

        if (rice == null) {

            rice = riceRepository.findById(id)
                    .orElseGet(() -> {
                        Rice newRice = neisUtil.findRice("json",
                                        "G10",
                                        "7430310",
                                        String.valueOf(year * 10000 + month * 100 + day))
                                .stream()
                                .filter(rice1 -> rice1.getRiceType().equals(riceType))
                                .findFirst()
                                .orElseThrow(()->RiceNotFoundException.EXCEPTION);
                        return newRice;
                    });

            mealCache.put(id, rice);
        }

        return RiceResponse.builder()
                .riceType(riceType.getTag())
                .riceId(id)
                .item(rice.getItem())
                .build();
    }

    public RiceTodayResponse findTodayRice(){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = curDate.getDayOfMonth();
        int month = curDate.getMonthValue();
        int year = curDate.getYear();

        List<RiceResponse> riceResponseList = new ArrayList<>();

        RiceType[] riceTypes = {RiceType.BREAKFAST, RiceType.LUNCH, RiceType.DINNER};

        for (RiceType riceType : riceTypes) {
            RiceRequest riceRequest = RiceRequest.builder()
                    .riceType(riceType)
                    .day(day)
                    .month(month)
                    .year(year)
                    .build();

            RiceResponse riceResponse = findRice(riceRequest);
            riceResponseList.add(riceResponse);
        }

        return RiceTodayResponse.builder()
                .responseList(riceResponseList)
                .build();
    }

    public void downLoadAllRice(int month){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = YearMonth.now().atEndOfMonth().getDayOfMonth();

        int count=1;
        while(count<=day){
            try {
                neisUtil.findRice("json",
                        "G10",
                        "7430310",
                        String.valueOf(curDate.getYear()*10000+month*100+count));
            } finally {
                count++;
            }
        }
    }

    public void setMukgenPick(
            MukgenPickRequest request
    ){

        User user = userFacade.currentUser();

        int addId = switch (request.getRiceType()) {
            case BREAKFAST -> 1;
            case LUNCH -> 2;
            case DINNER -> 3;
        };

        int id = (LocalDateTime.now().getYear() * 10000 + request.getMonth() * 100 + request.getDay()) * 10 + addId;

        if(!user.getRole().equals(UserRole.ADMIN)){
            throw NoPermissionException.EXCEPTION;
        }

        MukgenPick mukgenPick = MukgenPick.builder()
                .riceId(id)
                .riceType(request.getRiceType())
                .day(request.getDay())
                .month(request.getMonth())
                .build();

        mukgenPickRepository.save(mukgenPick);

    }

    public MukgenPickResponse findMukgenPick(){

        MukgenPick mukgenPick = mukgenPickRepository.findFirstByOrderByCreatedAtDesc()
                .orElseThrow(() -> MukgenPickNotFoundException.EXCEPTION);

        return MukgenPickResponse.of(mukgenPick);
    }

}
