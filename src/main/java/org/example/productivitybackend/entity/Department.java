package org.example.productivitybackend.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "departments")
public class Department {
    @Id
    private String id;
    private String name;
    private List<String> userIds;

}
