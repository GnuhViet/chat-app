package com.example.demo.chat;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

    // private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatClient sendMessage(@Payload ChatClient chatClient) {
        return chatClient;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatClient addUser(
            @Payload ChatClient chatClient,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in websocket session
        headerAccessor.getSessionAttributes().put("username", chatClient.getSender());
        return chatClient;
    }
}
