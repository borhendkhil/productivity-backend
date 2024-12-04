package org.example.productivitybackend.controller;



import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Department;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.service.DepartmentService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Department")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class DepartmentController {


    private final DepartmentService departmentService;



    @GetMapping("/departments")

    public Department getDepartments(@PathVariable String DepartmentId ) {
        return departmentService.getDepartmentById(DepartmentId);
    }


    @PostMapping("/createDepartment")
    @PreAuthorize("hasRole('ADMIN')")

    public void createDepartment(@RequestBody Department department) {
        departmentService.createDepartment(department);
    }
    @GetMapping("getAllDepartments")

    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();


    }


    @PostMapping("/updateDepartment")
    public Department updateDepartment(@PathVariable String DepartmentId, @RequestBody Department department, @PathVariable String userId) {
        return departmentService.updateDepartment(DepartmentId, department, userId);
    }

    @GetMapping("/getUsersbyDepartment")
    public String getUsersbyDepartment(@RequestBody String DepartmentId) {
        return departmentService.getUserIdsByDepartment(DepartmentId);
    }





}
