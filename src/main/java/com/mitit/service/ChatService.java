package com.mitit.service;

import com.mitit.domain.chat.Chat;
import com.mitit.domain.Subcategory;
import com.mitit.domain.chat.State;
import com.mitit.domain.chat.Status;
import com.mitit.dto.ChatDto;
import com.mitit.exception.alreadyexists.ChatAlreadyExistsException;
import com.mitit.exception.notfound.ChatNotFoundException;
import com.mitit.repository.ChatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final SubcategoryService subcategoryService;

    public void updateSubscription(Long chatId, Long subcategoryId) {
        Chat chat = chatRepo.findByChatId(chatId)
                .orElseThrow(() -> new ChatNotFoundException(
                        String.format("Failed to add subcategory with id %s to subscriptions - chat with id %s was not found",
                                subcategoryId, chatId))
                );

        for (Subcategory s : chat.getSubcategories()) {
            if (s.getId().equals(subcategoryId)) {
                chat.getSubcategories().remove(s);
                chatRepo.save(chat);
                return;
            }
        }

        chat.getSubcategories().add(subcategoryService.getSubcategoryById(subcategoryId));
        chatRepo.save(chat);
    }

    public Chat getChatById(Long chatId) {
        return chatRepo.findByChatId(chatId)
                .orElseThrow(() -> new ChatNotFoundException(
                        String.format("Chat with id %s was not found", chatId))
                );
    }

    public Chat save(Chat chat) {
        return chatRepo.save(chat);
    }

    public Chat createChat(Chat chat) {
        if (chatRepo.existsById(chat.getChatId())) {
            throw new ChatAlreadyExistsException(
                    String.format("Failed to save new chat - chat with id %s already exists", chat.getChatId())
            );
        }

        return save(chat);
    }

    public List<Chat> getAllChats() {
        return chatRepo.findAll();
    }

    public void updateStatus(Chat chat, Status status) {
        chatRepo.updateStatusByChatId(status, chat.getChatId());
    }

    public void updateState(Chat chat, State state) {
        chatRepo.updateStateByChatId(state, chat.getChatId());
    }

    public void updateLastMessageDateTime(Chat chat, LocalDateTime lastMessageDateTime) {
        chatRepo.updateLastMessageDateTimeByChatId(lastMessageDateTime, chat.getChatId());
    }
}
