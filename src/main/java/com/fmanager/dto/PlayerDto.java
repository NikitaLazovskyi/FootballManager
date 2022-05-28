package com.fmanager.dto;

import com.fmanager.entity.Team;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Long id;

    private String name;

    private String lastname;

    private Integer age;

    private Integer experience;

    private Long teamId;

    private BigDecimal cost;
}
