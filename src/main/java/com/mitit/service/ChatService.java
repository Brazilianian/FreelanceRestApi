package com.mitit.service;

import com.mitit.domain.Chat;
import com.mitit.domain.Subcategory;
import com.mitit.repository.ChatRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final SubcategoryService subcategoryService;

    public void updateSubscription(Long chatId, Long subcategoryId) {
        Chat chat = chatRepo.findByChatId(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat was not found"));

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
        return chatRepo.findByChatId(chatId).orElseThrow(() -> new EntityNotFoundException("Chat was not found"));
    }

    public Chat save(Chat chat) {
        return chatRepo.save(chat);
    }
}
