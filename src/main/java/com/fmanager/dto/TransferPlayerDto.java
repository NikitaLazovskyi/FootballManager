package com.fmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferPlayerDto {
    private Long playerId;
    private Long teamId;
}
