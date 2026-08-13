package com.example.weekly_planner.Repositories;

import com.example.weekly_planner.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository  <Task,Long> {
}
