package org.example.productivitybackend.service;


import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Message;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.repository.MessageRepository;
import org.example.productivitybackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    @Autowired private RoomRepository roomRepository;
    public Room createRoom(String name, List<User> participants) {
        Room room = new Room();
        room.setName(name);
        room.setUsers(participants);
        return roomRepository.save(room);
    }
    public Room getRoom(String id) {
        return roomRepository.findById(id).orElse(null);
    }
}
