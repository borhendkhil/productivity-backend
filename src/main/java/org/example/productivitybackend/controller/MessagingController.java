package org.example.productivitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.service.MessageService;
import org.example.productivitybackend.service.NotificationService;
import org.example.productivitybackend.service.RoomService;
import org.example.productivitybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class MessagingController {

    @Autowired
    private final MessageService MessageService;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;

     @Autowired
     private NotificationService notificationService;


    @PostMapping("/send")
    public void sendMessage(@RequestBody String senderId, @RequestBody String roomId, @RequestBody String content) {
         User sender = userService.findByID(senderId);
         Room room = roomService.getRoom(roomId);
         MessageService.sendMessage(sender, room, content);
        //
        for (User participant : room.getUsers()) {
            if (!participant.getId().equals(sender.getId())) {
                String targetToken = participant.getDeviceToken();
                notificationService.sendNotification(targetToken, "New Message in " + room.getName(), content);
            }
        }
    }
}
