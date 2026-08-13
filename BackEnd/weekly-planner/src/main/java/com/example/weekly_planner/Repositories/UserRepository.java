package com.example.weekly_planner.Repositories;

import com.example.weekly_planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
