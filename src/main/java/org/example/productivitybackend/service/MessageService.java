package org.example.productivitybackend.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.example.productivitybackend.entity.Message;
import org.example.productivitybackend.entity.Room;
import org.example.productivitybackend.entity.User;
import org.example.productivitybackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    @Autowired

    private final MessageRepository messageRepository;

    @Autowired
    private final RoomService roomService;




    public Message sendMessage(User sender, Room room, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setRoom(room);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message); }

    public List<Message> fetchMessages(String roomId) {
        ObjectId roomObjectId = new ObjectId(roomId);
        return messageRepository.findByRoom(roomObjectId);
    }


}
