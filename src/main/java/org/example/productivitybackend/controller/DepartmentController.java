package org.example.productivitybackend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Department;
import org.example.productivitybackend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/Department")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Department",description = "Operations pertaining to Department")
public class DepartmentController {


    private final DepartmentService departmentService;



    @GetMapping("/departments")
    @ApiOperation(value = "Get all departments")
    public Department getDepartments(@PathVariable String DepartmentId ) {
        return departmentService.getDepartmentById(DepartmentId);
    }


    @PostMapping("/createDepartment")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a new department")
    public void createDepartment(@RequestBody Department department) {
        departmentService.createDepartment(department);
    }
    @GetMapping("getAllDepartments")
    @ApiOperation(value = "Get all departments")
    public void getAllDepartments() {
        departmentService.getAllDepartments();
    }





}
