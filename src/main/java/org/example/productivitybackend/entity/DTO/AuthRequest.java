package org.example.productivitybackend.entity.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

}
