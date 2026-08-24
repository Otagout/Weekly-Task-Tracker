package com.example.weekly_planner.Controllers;

import com.example.weekly_planner.Repositories.UserRepository;
import com.example.weekly_planner.entity.User;
import com.example.weekly_planner.sevice.UserService;
 import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

        private final UserRepository userRepository;
        private final UserService userService;

        public UserController(UserRepository userRepository, UserService userService) {
                this.userRepository = userRepository;
                this.userService = userService;
        }

        @PostMapping
        public User createUser(@RequestBody User user) {
                return userRepository.save(user);
        }

        @GetMapping("/me")
        public ResponseEntity<User> authenticatedUser() {
                Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();

                User currentUser = (User) authentication.getPrincipal();

                return ResponseEntity.ok(currentUser);
        }

        @GetMapping("/")
        public ResponseEntity<List<User>> allUsers(){
                List<User> users =  userService.allUser();
                return ResponseEntity.ok(users);
        }




}