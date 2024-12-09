package org.example.productivitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.config.CustomUserDetails;
import org.example.productivitybackend.entity.DTO.MessageDto;
import org.example.productivitybackend.entity.Message;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.service.MessageService;
import org.example.productivitybackend.service.NotificationService;
import org.example.productivitybackend.service.RoomService;
import org.example.productivitybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class MessagingController {

    @Autowired
    private final MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;

     @Autowired
     private NotificationService notificationService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    @PostMapping("/send")
    public void sendMessage(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody MessageDto messageDto) {
         String senderId = userDetails.getId();
         User sender = userService.findByID(senderId);
         Room room = roomService.getRoom(messageDto.getRoomId());
         messageService.sendMessage(sender, room, messageDto.getContent());


         //simpMessagingTemplate.convertAndSend("/topic/" + messageDto.getRoomId(), messageDto);



    }




    @PostMapping("/getMessages")
    public List<Message> fetchMessages(@RequestBody Map<String, String> payload) {
        String roomId = payload.get("roomId");
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("roomId is required");
        }
        System.out.println("Fetching messages for roomId: " + roomId);
        return messageService.fetchMessages(roomId);
    }

}
