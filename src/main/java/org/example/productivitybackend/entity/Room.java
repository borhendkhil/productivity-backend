package org.example.productivitybackend.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<User> users;
    @DBRef
    private List<Message> messages;

}
