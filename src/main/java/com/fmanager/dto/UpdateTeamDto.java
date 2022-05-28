package com.fmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTeamDto {
    private Long id;
    private String name;
    private Integer commission;
}
