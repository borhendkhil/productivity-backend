package org.example.productivitybackend.repository;

import org.bson.types.ObjectId;
import org.example.productivitybackend.entity.Message;
import org.example.productivitybackend.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{ 'room.$id': ?0 }")
    List<Message> findByRoom(ObjectId roomId);
}
