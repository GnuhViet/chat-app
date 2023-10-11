package com.example.demo.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatClient {
    private String sender;
    private String content;
    private String roomID;
    private MessageType type;
}
