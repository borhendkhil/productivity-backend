package org.example.productivitybackend.repository;

import org.example.productivitybackend.entity.Department;
import org.example.productivitybackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {



}
