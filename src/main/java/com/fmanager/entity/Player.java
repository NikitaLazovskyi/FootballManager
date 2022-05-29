package com.fmanager.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private LocalDate startCareer;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Team team;
    private BigDecimal cost;
}
