package com.example.msuser.cron;

import com.example.msuser.service.ConsumerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ActivateCardCron {
    ConsumerService consumerService;
    @Scheduled(fixedDelayString = "P8TS")
    @SchedulerLock(name = "update-any-info-in-something",
    lockAtLeastFor = "PT15S",
    lockAtMostFor = "PT30M")
    public void updateAnyData(){
        consumerService.activateCard();
    }
}
