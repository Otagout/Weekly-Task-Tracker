package com.example.weekly_planner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "weeks")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate weekStart;

    private LocalDate weekEnd;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
}