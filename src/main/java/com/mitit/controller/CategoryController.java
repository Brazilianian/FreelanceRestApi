package com.mitit.controller;

import com.mitit.domain.Category;
import com.mitit.dto.CategoryDto;
import com.mitit.mapper.CategoryMapper;
import com.mitit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @ResponseBody
    public List<CategoryDto> getCategoriesByFreelanceSite(@RequestParam(name = "freelance_site") String freelanceSiteName) {
        List<Category> categories = categoryService.getCategoriesByFreelanceSiteName(freelanceSiteName);
        return categories
                .stream()
                .map(categoryMapper::fromObjectToJson)
                .collect(Collectors.toList());
    }

    @GetMapping("/{category_id}")
    public CategoryDto getCategoryById(@PathVariable(name = "category_id") Long categoryId) {
        return categoryMapper.fromObjectToJson(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/{category_id}/chats/{chat_id}")
    @ResponseBody
    public boolean chooseSubscriptionByCategoryId(@PathVariable(name = "category_id") Long categoryId,
                                                  @PathVariable(name = "chat_id") Long chatId) {
        categoryService.updateSubscription(categoryId, chatId);
        return true;
    }
}
