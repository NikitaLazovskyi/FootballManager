package com.fmanager.service;

import com.fmanager.entity.Player;
import com.fmanager.entity.Team;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidationService {
    private static final String pName = "^[a-zA-Z\\s-]{2,}$";

    public static boolean validate(Player player){
        return player.getName() != null
                && player.getLastname() != null
                && player.getStartCareer() != null
                && player.getDateOfBirth() != null
                && player.getTeam() != null
                && ComputingService.computeAge(player.getDateOfBirth()) >= 18
                && ComputingService.computeExperience(player.getStartCareer()) >= 0
                && player.getName().matches(pName)
                && player.getLastname().matches(pName);
    }

    public static boolean validate(Team team){
        return team.getCommission() != null
                && team.getName() != null
                && team.getBankAccount() != null
                && team.getBankAccount().compareTo(new BigDecimal(0)) >= 0
                && team.getCommission() >= 0
                && team.getCommission() <= 10
                && team.getName().matches(pName);
    }
}
