package org.example.productivitybackend.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "messages")


public class Message {
    @Id
    private String id;
    @DBRef
    private User sender;
    private String content;
    @DBRef
    private Room room;
    private LocalDateTime timestamp ;

}
