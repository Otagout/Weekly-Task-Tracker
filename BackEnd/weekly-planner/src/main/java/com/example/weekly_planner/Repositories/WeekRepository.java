package com.example.weekly_planner.Repositories;

import com.example.weekly_planner.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRepository  extends JpaRepository<Week ,Long> {
}
