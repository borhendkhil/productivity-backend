package org.example.productivitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.config.CustomUserDetails;
import org.example.productivitybackend.entity.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import org.example.productivitybackend.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")


public class TaskController {

    private final TaskService taskService;


    @GetMapping("/tasks")
    public List<Task> getTasksByAssigneeId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String assigneeId = userDetails.getId();
        System.out.println("assigneeId: " + assigneeId);
        return taskService.getTasksByAssigneeId(assigneeId);
    }
    @PostMapping("/createTask")
    public void createTask(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Task task) {
        String creatorId = userDetails.getId();
        taskService.createTask(creatorId, task);
    }
    @PutMapping("/updateTask/{taskId}")
    public void updateTask(@PathVariable String taskId, @RequestBody Task task){
        taskService.update(taskId, task);
    }
    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestBody String taskId){
        taskService.delete(taskId);
    }

    @DeleteMapping("/deleteallTask")
    public void deleteallTask(){
        taskService.deletealltasks();
    }



}
