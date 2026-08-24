package com.example.weekly_planner.sevice;

import com.example.weekly_planner.Repositories.UserRepository;
import com.example.weekly_planner.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository ,EmailService emailService){
        this.userRepository = userRepository;
    }

    public List<User> allUser(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add );
        return users;
    }

}
