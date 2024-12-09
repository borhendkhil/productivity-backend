package org.example.productivitybackend.controller;


import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.config.CustomUserDetails;
import org.example.productivitybackend.entity.DTO.CreationRoomDto;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    @Autowired
    private final RoomService roomService;


    @PostMapping("/createRoom")
    public void createRoom(@RequestBody CreationRoomDto creationRoomDto) {

        roomService.createRoom(creationRoomDto.getName(), creationRoomDto.getUsers());
    }
    @PostMapping("/getRoom")
    public Room getRoom(@RequestBody String id) {

        return roomService.getRoom(id);
    }

    @GetMapping("/getRoomsByUser")
    public List<Room> getRoomsByUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String id = userDetails.getId();
        return roomService.getRoomsByUser(id);
    }

    @DeleteMapping("/deleteRoom")
    public void deleteRoom(@RequestBody String id) {

        roomService.deleteRoom(id);
    }

    @DeleteMapping("/deleteAllRooms")
    public void deleteAllRooms() {
        roomService.deleteAllRooms();
    }


}
