package com.example.weekly_planner.Controllers;


import com.example.weekly_planner.Repositories.TaskRepository;
import com.example.weekly_planner.Repositories.WeekRepository;
import com.example.weekly_planner.entity.Task;
import com.example.weekly_planner.entity.Week;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final WeekRepository weekRepository;

    public TaskController(TaskRepository taskRepository , WeekRepository weekRepository){
        this.weekRepository= weekRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public Task createTask(@RequestParam("week_id") Long WeekId , @RequestBody Task task){
        Week week = weekRepository.findById(WeekId)
                .orElseThrow();

        task.setWeek(week);

        return taskRepository.save(task);


    }


}
