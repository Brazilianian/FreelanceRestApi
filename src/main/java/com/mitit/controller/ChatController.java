package com.mitit.controller;

import com.mitit.domain.FreelanceSite;
import com.mitit.domain.chat.Chat;
import com.mitit.domain.chat.State;
import com.mitit.domain.chat.Status;
import com.mitit.dto.ChatDto;
import com.mitit.mapper.ChatMapper;
import com.mitit.service.CategoryService;
import com.mitit.service.ChatService;
import com.mitit.service.FreelanceSiteService;
import com.mitit.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final ChatMapper chatMapper;
    private final CategoryService categoryService;
    private final FreelanceSiteService freelanceSiteService;

    @GetMapping
    public List<ChatDto> getChats(@RequestParam(name = "status", required = false) Status status,
                                  @RequestParam(name = "state", required = false) State state) {
        return chatService.getAllChats()
                .stream()
                .filter(chat -> (state == null || chat.getState() == state)
                        && (status == null || chat.getStatus() == status))
                .map(chatMapper::fromObjectToDto)
                .toList();
    }

    @GetMapping("/{chat_id}")
    public ChatDto getChatByChatId(@PathVariable(name = "chat_id") Long chatId) {
        return chatMapper.fromObjectToDto(chatService.getChatById(chatId));
    }

    @PostMapping
    public ChatDto addChat(@RequestBody ChatDto chatDto) {
        Chat chat = chatService.createChat(chatMapper.fromDtoToObject(chatDto));

        // Add all subscriptions for new chat
        for (FreelanceSite freelanceSite : freelanceSiteService.getFreelanceSites()) {
            categoryService.addSubscriptionsToAllCategoriesOfFreelanceSite(freelanceSite.getName(), chat.getChatId());
        }

        return chatMapper.fromObjectToDto(chat);
    }

    @PutMapping("/{chat_id}/status")
    public ChatDto updateChatStatus(@PathVariable(name = "chat_id") Long chatId,
                                    @RequestParam(name = "status") Status status) {
        Chat chat = chatService.getChatById(chatId);
        chatService.updateStatus(chat, status);
        return chatMapper.fromObjectToDto(chatService.getChatById(chatId));
    }

    @PutMapping("/{chat_id}/state")
    public ChatDto updateChatState(@PathVariable(name = "chat_id") Long chatId,
                                   @RequestParam(name = "state") State state) {
        Chat chat = chatService.getChatById(chatId);
        chatService.updateState(chat, state);
        return chatMapper.fromObjectToDto(chatService.getChatById(chatId));
    }

    @PutMapping("/{chat_id}/last_message_datetime")
    public ChatDto updateLastMessageDatetime(@PathVariable("chat_id") Long chatId,
                                             @RequestParam("last_message_datetime") String dateTime) {
        Chat chat = chatService.getChatById(chatId);
        chatService.updateLastMessageDateTime(chat, DateUtil.parseDateByPattern(dateTime, "yyyy-MM-dd HH:mm:ss"));
        return chatMapper.fromObjectToDto(chatService.getChatById(chatId));
    }
}
