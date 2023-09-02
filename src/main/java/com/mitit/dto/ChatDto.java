package com.mitit.dto;

import com.mitit.domain.Chat;
import lombok.Getter;

@Getter
public class ChatDto {
    private final Long chat_id;
    private final String username;
    private final String first_name;
    private final String last_name;
    private final String status;
    private final String state;

    private ChatDto(Long chatId, String username, String firstName, String lastName, String status, String state) {
        chat_id = chatId;
        this.username = username;
        first_name = firstName;
        last_name = lastName;
        this.status = status;
        this.state = state;
    }

    public static ChatDto from(Chat chat) {
        return new ChatDto(chat.getChatId(),
                chat.getUsername(),
                chat.getFirstName(),
                chat.getLastName(),
                chat.getStatus(),
                chat.getState());
    }
}
