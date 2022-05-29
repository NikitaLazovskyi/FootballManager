package com.fmanager.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComputingService {
    public static BigDecimal computeCost(LocalDate dob, LocalDate startCareer){
        int age = computeAge(dob);
        int experience = computeExperience(startCareer);
        return BigDecimal.valueOf(experience * 100_000 / (double) age);
    }

    public static int computeAge(LocalDate dob){
        return dob.until(LocalDate.now()).getYears();
    }

    public static int computeExperience(LocalDate startCareer){
        int monthsOfCareerRest = startCareer.until(LocalDate.now()).getMonths();
        int yearsOfCareer = startCareer.until(LocalDate.now()).getYears();
        int experience = (yearsOfCareer * 12) + monthsOfCareerRest;

        return experience;
    }
}
