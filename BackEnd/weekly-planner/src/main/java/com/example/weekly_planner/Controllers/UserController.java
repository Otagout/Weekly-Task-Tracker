package com.example.weekly_planner.Controllers;


import com.example.weekly_planner.Repositories.UserRepository;
import com.example.weekly_planner.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
private final UserRepository userRepository;

public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
}

@PostMapping
        public User createUser(@RequestBody User user){
        return  userRepository.save(user);
}
}
