package com.fmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString(of = {"name","lastname","age","experience"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private Integer age;
    private Integer experience;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Team team;
    private BigDecimal cost;
}
