package com.example.mukgen.domain.rice.service;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class RiceScheduledService {

    private final RiceService riceService;

    @Scheduled(cron = "0 0 0 1 * ?", zone = "Asia/Seoul")
    public void autoDownLoadRice(){
        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        riceService.downLoadAllRice(curDate.getMonthValue());
    }

    // 아침 7시 30분에 실행
    @Scheduled(cron = "0 30 7 * * ?", zone = "Asia/Seoul")
    public void morningTask() {

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        List<String> titleList = Arrays.asList(
                "맛있는 아침 먹고 오늘도 힘내보아요! 😊",
                "맛있는 아침 먹고, 개발하러 갈까요? 🔥",
                "개발하려면 맛있는 아침식사는 필수겠죠? 😝",
                "야, 오늘 아침 뭐야? 👀",
                "오늘의 아침은?? 👀"
        );
        riceService.sendPushMessage(titleList.get(random.nextInt(5)), RiceType.BREAKFAST);
    }

    // 오후 12시 30분에 실행
    @Scheduled(cron = "0 30 12 * * ?", zone = "Asia/Seoul")
    public void afternoonTask() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        List<String> titleList = Arrays.asList(
                "오늘 점심은?? 👀",
                "점심 맛있었으면 좋겠다.. 🤤",
                "점심 궁금하죠? 😇",
                "점심 🍚",
                "배고프다.. 🤤"
        );
        riceService.sendPushMessage(titleList.get(random.nextInt(5)), RiceType.LUNCH);
    }

    // 오후 5시 30분에 실행
    @Scheduled(cron = "0 30 17 * * ?", zone = "Asia/Seoul")
    public void eveningTask() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        List<String> titleList = Arrays.asList(
                "저녁먹고 남은시간 힘내봐요!! 🔥",
                "저녁 🍕",
                "오늘 저녁 맛있어?",
                "배고프다 오늘 저녁 뭐야? 🙃",
                "오늘 하루 어떠셨나요? 오늘 저녁먹고 힘내봐요 😄"
        );
        riceService.sendPushMessage(titleList.get(random.nextInt(5)), RiceType.DINNER);
    }

}
