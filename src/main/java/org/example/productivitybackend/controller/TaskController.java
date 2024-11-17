package org.example.productivitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.config.CustomUserDetails;
import org.example.productivitybackend.entity.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import org.example.productivitybackend.service.TaskService;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")


public class TaskController {

    private final TaskService taskService;


    @GetMapping("/tasks")
    public void getTasksByAssigneeId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String assigneeId = userDetails.getId();
        taskService.getTasksByAssigneeId(assigneeId);
    }
    @PostMapping("/createTask")
    public void createTask(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody Task task) {
        String creatorId = userDetails.getId();
        taskService.createTask(creatorId, task);
    }
    @PutMapping("/updateTask")
    public void updateTask(@RequestBody String taskId){
        taskService.update(taskId);
    }
    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestBody String taskId){
        taskService.delete(taskId);
    }


}
