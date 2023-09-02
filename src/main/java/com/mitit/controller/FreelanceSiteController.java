package com.mitit.controller;

import com.mitit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/freelanceSite/")
public class FreelanceSiteController {

    private final CategoryService categoryService;

    @PostMapping("/{freelance_site}/chats/{chat_id}")
    public boolean addSubscriptionsToAllCategoriesOfFreelanceSite(@PathVariable(name = "chat_id") Long chatId,
                                                                  @PathVariable(name = "freelance_site") String freelanceSiteName) {
        categoryService.addSubscriptionsToAllCategoriesOfFreelanceSite(freelanceSiteName, chatId);
        return true;
    }
}
