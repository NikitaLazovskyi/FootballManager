package com.fmanager.dto;

import com.fmanager.entity.Team;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PlayerDto {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private Integer age;

    @NonNull
    private Integer experience;

    @NonNull
    private Long teamId;

    private BigDecimal cost;
}
