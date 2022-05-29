package com.fmanager.commands;

import com.fmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class UpdateCostCommand implements CommandLineRunner {

    private final PlayerService playerService;

    @Autowired
    public UpdateCostCommand(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        playerService.updateCostOfEachPlayer();
    }
}
