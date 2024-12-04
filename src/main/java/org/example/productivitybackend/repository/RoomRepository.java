package org.example.productivitybackend.repository;

import org.example.productivitybackend.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomRepository extends MongoRepository <Room, String> {

}
