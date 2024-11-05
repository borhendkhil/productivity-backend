package org.example.productivitybackend.entity.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

}
