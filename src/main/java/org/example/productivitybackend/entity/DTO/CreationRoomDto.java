package org.example.productivitybackend.entity.DTO;

import lombok.Data;
import org.example.productivitybackend.entity.User;

import java.util.List;

@Data
public class CreationRoomDto {

    private String name;
    private List<User> users;
}
