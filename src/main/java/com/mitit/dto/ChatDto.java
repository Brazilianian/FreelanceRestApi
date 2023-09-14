package com.mitit.dto;

import com.mitit.domain.chat.Chat;
import com.mitit.domain.chat.State;
import com.mitit.domain.chat.Status;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class ChatDto {
    private final Long chat_id;
    private final String username;
    private final String first_name;
    private final String last_name;
    private final String status;
    private final String state;
    private final LocalDateTime last_message_datetime;

    private ChatDto(Long chatId, String username, String firstName, String lastName,
                    String status, String state, LocalDateTime last_message_datetime) {
        this.chat_id = chatId;
        this.username = username;
        this.first_name = firstName;
        this.last_name = lastName;
        this.status = status;
        this.state = state;
        this.last_message_datetime = last_message_datetime;
    }

    public static ChatDto from(Chat chat) {
        return new ChatDto(chat.getChatId(),
                chat.getUsername(),
                chat.getFirstName(),
                chat.getLastName(),
                chat.getStatus().toString(),
                chat.getState().toString(),
                chat.getLastMessageDateTime());
    }
}
