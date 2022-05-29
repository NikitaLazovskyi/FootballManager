package com.fmanager.commands;

import com.fmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Order(value = 1)
@EnableScheduling
@Component
public class UpdateCostCommand{

    private final PlayerService playerService;

    @Autowired
    public UpdateCostCommand(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * One time per two week will check cost
     */
    @Scheduled(fixedRate = 1000/*millis*/ * 60/*sek*/ * 60/*min*/ * 24/*hours*/ * 14/*days*/)
    public void run(){
        playerService.updateCostOfEachPlayer();
    }
}
