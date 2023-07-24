package com.example.mukgen.domain.rice.service;


import com.example.mukgen.domain.rice.controller.dto.request.RiceRequest;
import com.example.mukgen.domain.rice.controller.dto.response.MukgenPickResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceMonthListResponse;
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
import com.example.mukgen.infra.feign.gpt.GptFeignClient;
import com.example.mukgen.infra.feign.gpt.GptFeignRequest;
import com.example.mukgen.infra.feign.gpt.GptResponse;
import com.example.mukgen.infra.feign.gpt.Message;
import com.example.mukgen.infra.feign.rice.NeisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final GptFeignClient gptFeignClient;

    private final ObjectMapper objectMapper;

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
                                        String.valueOf(year * 10000 + month * 100 + day),riceType);// 20230000
                        return newRice;
                    });

            mealCache.put(id, rice);
        }

        return RiceResponse.of(rice);
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

        User user = userFacade.currentUser();

        if(!user.getRole().equals(UserRole.ADMIN)){
            throw NoPermissionException.EXCEPTION;
        }

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = YearMonth.now().atEndOfMonth().getDayOfMonth();

        int count=1;
        while(count<=day){
            try {
                RiceType[] riceTypes = {RiceType.LUNCH, RiceType.BREAKFAST, RiceType.DINNER};
                for (RiceType riceType : riceTypes) {
                    Rice rice = neisUtil.findRice("json",
                            "G10",
                            "7430310",
                            String.valueOf(curDate.getYear()*10000+curDate.getMonthValue()*100+count),
                            riceType);
                    riceRepository.save(rice);
                }
            } finally {
                count++;
            }
        }
    }
    public void autoDownLoadAllRice(int month){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = YearMonth.now().atEndOfMonth().getDayOfMonth();

        int count=1;
        while(count<=day){
            try {
                RiceType[] riceTypes = {RiceType.LUNCH, RiceType.BREAKFAST, RiceType.DINNER};
                for (RiceType riceType : riceTypes) {
                    Rice rice = neisUtil.findRice("json",
                            "G10",
                            "7430310",
                            String.valueOf(curDate.getYear()*10000+curDate.getMonthValue()*100+count),
                            riceType);
                    riceRepository.save(rice);
                }
            } finally {
                count++;
            }
        }
    }

    public MukgenPick setMukgenPick() throws JsonProcessingException {

        User user = userFacade.currentUser();

        if(user.getRole()!=UserRole.ADMIN){
            throw NoPermissionException.EXCEPTION;
        }

        Rice rice = riceRepository.findById(findMukgenPickGpt())
                .orElseThrow(() -> RiceNotFoundException.EXCEPTION);

        MukgenPick mukgenPick = MukgenPick.builder()
                .riceId(rice.getId())
                .riceType(rice.getRiceType())
                .day(Integer.parseInt(String.valueOf(rice.getId()).substring(6,8)))
                .month(Integer.parseInt(String.valueOf(rice.getId()).substring(4,6)))
                .build();

        return mukgenPickRepository.save(mukgenPick);

    }
    public MukgenPick setMukgenPickAuto() throws JsonProcessingException {

        Rice rice = riceRepository.findById(findMukgenPickGpt())
                .orElseThrow(() -> RiceNotFoundException.EXCEPTION);

        MukgenPick mukgenPick = MukgenPick.builder()
                .riceId(rice.getId())
                .riceType(rice.getRiceType())
                .day(Integer.parseInt(String.valueOf(rice.getId()).substring(6,8)))
                .month(Integer.parseInt(String.valueOf(rice.getId()).substring(4,6)))
                .build();

        return mukgenPickRepository.save(mukgenPick);

    }

    public MukgenPickResponse findMukgenPick(){

        MukgenPick mukgenPick = mukgenPickRepository.findFirstByOrderByCreatedAtDesc()
                .orElseThrow(() -> MukgenPickNotFoundException.EXCEPTION);

        return MukgenPickResponse.of(mukgenPick);
    }

    public RiceMonthListResponse findMonthRices(int month){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int filterId = (curDate.getYear() * 10000 + curDate.getMonthValue() * 100 + curDate.getDayOfMonth()) * 10;
        return RiceMonthListResponse.builder()
                .riceResponseList(riceRepository.findAllByMonth(month).stream()
                        .filter(riceId -> riceId.getId()>filterId)
                        .map(RiceResponse::of)
                        .toList())
                        .build();

    }

    private int findMukgenPickGpt() throws JsonProcessingException {
        StringBuilder riceId = new StringBuilder();
        String answerMessage = "";
        RiceMonthListResponse monthRices = findMonthRices(LocalDateTime.now().getMonthValue());
        String riceListJson = objectMapper.writeValueAsString(monthRices.getRiceResponseList());
        answerMessage+=riceListJson;
        answerMessage+="위에있는 급식 을 보고 너의 어떠한 주관이 포함되어도 상관 없으니 가장 맛있을것 같은 급식을 하나만 선택해서 다른말 하지말고 riceId만 말해 급식정보 필요없어";
        List<Message> messages = new ArrayList<>();
        Message message = new Message("user", answerMessage);
        messages.add(message);
        GptFeignRequest gptFeignRequest = GptFeignRequest.builder()
                .messages(messages)
                .model("gpt-3.5-turbo-16k")
                .build();
        GptResponse response = gptFeignClient.getResponse(gptFeignRequest);
        String content="";
        List<GptResponse.choice> choices = response.getChoices();
        for (GptResponse.choice choice : choices) {
            Message message1 = choice.getMessage();
            content = message1.getContent();
        }
        for(int i=0;i<content.length();i++){
            if(content.charAt(i)>='0'&&content.charAt(i)<='9'){
                riceId.append(content.charAt(i));
            }
        }
        return Integer.parseInt(riceId.toString());
    }

}
