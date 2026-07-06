package com.pramod.firstapi.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmployeeScheduler {

    @Scheduled(fixedRate = 30000)
    public void printMessage() {

        System.out.println(
                "Scheduler Running : "
                + LocalDateTime.now());

       // System.out.println("Employee Report Generated");
    }

}