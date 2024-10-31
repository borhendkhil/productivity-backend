package org.example.productivitybackend.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String createdAt;
    private String updatedAt;
    private List<String> assigneeId;

    private String creatorId;
    private String departmentId;



}
