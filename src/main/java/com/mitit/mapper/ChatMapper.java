package com.mitit.mapper;

import com.mitit.domain.chat.Chat;
import com.mitit.domain.chat.State;
import com.mitit.domain.chat.Status;
import com.mitit.dto.ChatDto;
import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatDto fromObjectToDto(Chat chat) {
        return ChatDto.from(chat);
    }

    public Chat fromDtoToObject(ChatDto chatDto) {
        return new Chat
                .Builder(chatDto.getChat_id())
                .username(chatDto.getUsername())
                .firstName(chatDto.getFirst_name())
                .lastName(chatDto.getLast_name())
                .status(Status.valueOf(chatDto.getStatus()))
                .state(State.valueOf(chatDto.getState()))
                .build();
    }
}
