package org.example.productivitybackend.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Department;
import org.example.productivitybackend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }
    public void deleteDepartment(String departmentId) {
        departmentRepository.deleteById(departmentId);
    }
    public Department getDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departmentId));
    }


    @PreAuthorize("hasRole('MANAGER')")
    public Department updateDepartment(String departmentId, Department department, String userId) {


        if ( userService.findByID(userId) == null){
            throw new IllegalArgumentException("unauthorized User " + userId);

        }
        else {
            Department existingDepartment = getDepartmentById(departmentId);
            existingDepartment.setName(department.getName());
            existingDepartment.setUserIds(department.getUserIds());
            return departmentRepository.save(existingDepartment);

        }

    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }





}
