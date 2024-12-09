package org.example.productivitybackend.service;


import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public List<Room> getRoomsByUser(String userId) {
        return roomRepository.findByUsersId(userId);
    }

    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }
}

