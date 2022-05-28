package com.fmanager.service;

import com.fmanager.entity.Player;
import com.fmanager.entity.Team;

import java.math.BigDecimal;

public class ValidationService {
    private static final String pName = "^[a-zA-Z\\s-]{2,}$";

    public static boolean validate(Player player){
        return player.getName() != null
                && player.getLastname() != null
                && player.getExperience() != null
                && player.getAge() != null
                && player.getTeam() != null
                && player.getAge() >= 18
                && player.getExperience() >= 0
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
