package com.mitit.controller;

import com.mitit.domain.Subcategory;
import com.mitit.dto.SubcategoryDto;
import com.mitit.mapper.SubcategoryMapper;
import com.mitit.service.ChatService;
import com.mitit.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;
    private final SubcategoryMapper subcategoryMapper;
    private final ChatService chatService;

    @GetMapping
    @ResponseBody
    public List<SubcategoryDto> getSubcategoriesByFreelanceSiteAndChat(@RequestParam(name = "freelance_site") String freelanceSiteName,
                                                                       @RequestParam(name = "chat_id") Long chat_id) {
        List<Subcategory> subcategories = subcategoryService
                .getSubcategoriesByFreelanceSiteNameAndChatId(freelanceSiteName, chat_id);

        return subcategories
                .stream()
                .map(subcategoryMapper::fromObjectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category")
    @ResponseBody
    public List<SubcategoryDto> getSubcategoriesByCategoryId(@RequestParam(name = "category_id") Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);

        return subcategories
                .stream()
                .map(subcategoryMapper::fromObjectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/chats/{chat_id}")
    @ResponseBody
    public List<SubcategoryDto> getChatSubscriptions(@PathVariable(name = "chat_id") Long chatId) {
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByChatId(chatId);

        return subcategories
                .stream()
                .map(subcategoryMapper::fromObjectToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{subcategory_id}")
    @ResponseBody
    public SubcategoryDto getSubcategoryById(@PathVariable(name = "subcategory_id") Long subcategoryId) {
        return subcategoryMapper.fromObjectToDto(subcategoryService.getSubcategoryById(subcategoryId));
    }

    @PostMapping("/{subcategory_id}/chats/{chat_id}")
    @ResponseBody
    public boolean updateChatSubscription(@PathVariable(name = "chat_id") Long chatId,
                                       @PathVariable(name = "subcategory_id") Long subcategoryId) {
        chatService.updateSubscription(chatId, subcategoryId);
        return true;
    }
}
