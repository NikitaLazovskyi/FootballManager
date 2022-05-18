package com.fmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private Integer commission;
    private BigDecimal bankAccount;
}
