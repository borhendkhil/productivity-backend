package org.example.productivitybackend.service;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Department;
import org.example.productivitybackend.entity.Task;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.repository.DepartmentRepository;
import org.example.productivitybackend.repository.TaskRepository;
import org.example.productivitybackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    public Task createTask(String creatorId, Task task) {
        // Retrieve the department by its ID
        Optional<Department> departmentOpt = departmentRepository.findById(task.getDepartmentId());

        if (departmentOpt.isEmpty()) {
            throw new IllegalArgumentException("Department not found with ID: " + task.getDepartmentId());
        }

        Department department = departmentOpt.get();
        List<String> departmentUsers = department.getUserIds();

        // Ensure all assignees are members of the department
        if (!departmentUsers.containsAll(task.getAssigneeId())) {
            throw new IllegalArgumentException("One or more assignees are not members of the specified department.");
        }

        // Set the creator's ID on the task
        task.setCreatorId(creatorId);

        // Save and return the created task
        return taskRepository.save(task);


    }

    public List<Task> getTasksByAssigneeId(String assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId);
    }
    public Task update(String taskId){
        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("Task not found with ID: " + taskId);
        }

        return taskRepository.save(taskOpt.get());
    }
    public void delete(String taskId){
        taskRepository.deleteById(taskId);
    }



}
