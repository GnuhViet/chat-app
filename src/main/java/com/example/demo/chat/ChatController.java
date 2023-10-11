package com.example.demo.chat;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/chat/{roomID}/send")
    public void sendMessage(
            @DestinationVariable String roomID,
            @Payload ChatClient chatClient
    ) {
        messageTemplate.convertAndSend("/topic/" + roomID, chatClient);
    }

    @MessageMapping("/chat/{roomID}/add")
    public void addUser(
            @DestinationVariable String roomID,
            @Payload ChatClient chatClient,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in websocket session
        headerAccessor.getSessionAttributes().put("client", chatClient);
        // send the message
        messageTemplate.convertAndSend("/topic/" + roomID, chatClient);
    }
}
