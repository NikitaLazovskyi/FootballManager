package com.fmanager.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Long id;

    private String name;

    private String lastname;

    private LocalDate dob;

    private LocalDate startCareer;

    private Long teamId;

    private BigDecimal cost;
}
