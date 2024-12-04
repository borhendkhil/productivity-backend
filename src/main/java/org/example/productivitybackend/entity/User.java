package org.example.productivitybackend.entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class User {
    @Id
    private String id;
    private String status;
    private String password;
    private String email;
    private String role;
    private String username;
    private Date ceatedAt;
    private String bio;
    private String deviceToken;



}
