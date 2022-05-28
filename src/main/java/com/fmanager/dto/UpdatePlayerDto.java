package com.fmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePlayerDto {
    private Long id;
    private String name;
    private String lastname;
}
