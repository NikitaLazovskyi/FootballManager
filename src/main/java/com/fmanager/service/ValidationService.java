package com.fmanager.service;

import com.fmanager.entity.Player;
import com.fmanager.entity.Team;

import java.math.BigDecimal;

public class ValidationService {
    private static final String pName = "^[-\\w\\s]+$";

    public static boolean validate(Player player){
        return player.getAge() >= 18
                && player.getExperience() >= 0
                && player.getName() != null
                && player.getName().matches(pName)
                && player.getLastname().matches(pName);
    }

    public static boolean validate(Team team){
        return team.getBankAccount().compareTo(new BigDecimal(0)) >= 0
                && team.getCommission() >= 0
                && team.getCommission() <= 10
                && team.getName() != null
                && team.getName().matches(pName);
    }
}
