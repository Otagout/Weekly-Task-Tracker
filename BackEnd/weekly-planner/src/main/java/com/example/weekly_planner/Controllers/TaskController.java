package com.example.weekly_planner.Controllers;


import com.example.weekly_planner.Repositories.TaskRepository;
import com.example.weekly_planner.Repositories.WeekRepository;
import com.example.weekly_planner.entity.Task;
import com.example.weekly_planner.entity.Week;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final WeekRepository weekRepository;

    public TaskController(TaskRepository taskRepository , WeekRepository weekRepository){
        this.weekRepository= weekRepository;
        this.taskRepository = taskRepository;
    }

     @GetMapping
     public  List<Task> getTasks(){
        return taskRepository.findAll();
     }
    @PostMapping
    public Task createTask(@RequestParam("week_id") Long WeekId , @RequestBody Task task){
        Week week = weekRepository.findById(WeekId)
                .orElseThrow();

        task.setWeek(week);

        return taskRepository.save(task);


    }
    @PutMapping
    public Task updateTask(@RequestParam("week_id") Long WeekId, @RequestBody Task task){
        Task existingTask = taskRepository.findById(task.getId())
                .orElseThrow();
        Week week = weekRepository.findById(WeekId)
                .orElseThrow();
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setWeek(week);
        return taskRepository.save(existingTask);

    }

    @DeleteMapping("/{id}")
    public void  deleteTask(@PathVariable Long id){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow();

         taskRepository.delete(existingTask);


    }


}
