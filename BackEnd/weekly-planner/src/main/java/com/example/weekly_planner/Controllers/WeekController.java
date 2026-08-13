package com.example.weekly_planner.Controllers;


import com.example.weekly_planner.Repositories.UserRepository;
import com.example.weekly_planner.Repositories.WeekRepository;
import com.example.weekly_planner.entity.User;
import com.example.weekly_planner.entity.Week;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weeks")

public class WeekController {


    private final WeekRepository weekRepository;
    private final UserRepository userRepository;

    public WeekController(WeekRepository  weekRepository ,UserRepository userRepository ){
        this.weekRepository = weekRepository;
                this.userRepository = userRepository;
    }

    @PostMapping
    public Week createWeek(@RequestParam("user_id") Long userId , @RequestBody Week week){
        User user = userRepository.findById(userId)
                .orElseThrow();
        week.setUser(user);
        return weekRepository.save(week);

    }
}
