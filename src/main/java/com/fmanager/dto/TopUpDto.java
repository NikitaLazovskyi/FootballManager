package com.fmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TopUpDto {
    private Long teamId;
    private BigDecimal amount;
}
