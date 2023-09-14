package com.mitit.service;

import com.mitit.domain.Category;
import com.mitit.domain.chat.Chat;
import com.mitit.domain.Subcategory;
import com.mitit.exception.notfound.CategoryNotFoundException;
import com.mitit.exception.notfound.SubcategoryNotFoundException;
import com.mitit.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SubcategoryService subcategoryService;
    private final CategoryRepo categoryRepo;
    private final ChatService chatService;

    public List<Category> getCategoriesByFreelanceSiteName(String freelanceSiteName) {
        return categoryRepo.findAllByFreelanceSite_Name(freelanceSiteName);
    }

    public void updateSubscriptionOfCategory(Long categoryId, Long chatId) {
        Chat chat = chatService.getChatById(chatId);
        Subcategory subcategory = chat.getSubcategories()
                .stream()
                .filter(s -> s.getCategory().getId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new SubcategoryNotFoundException(
                        String.format("Can`t find any subcategory of category with id %s by chat with id %s", categoryId, chatId))
                );

        if (subcategory == null) {
            List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);
            chat.getSubcategories().addAll(subcategories);
            chatService.save(chat);
            return;
        }

        chat.getSubcategories().removeIf(s -> s.getCategory().getId().equals(categoryId));
        chatService.save(chat);
    }

    public void addSubscriptionsToAllCategoriesOfFreelanceSite(String freelanceSiteName, Long chatId) {
        List<Category> categories = getCategoriesByFreelanceSiteName(freelanceSiteName);
        Chat chat = chatService.getChatById(chatId);

        long countOfSubsByCategory = chat.getSubcategories()
                .stream()
                .filter(s -> categories.contains(s.getCategory()))
                .count();

        if (countOfSubsByCategory == 0) {
            for (Category category : categories) {
                chat.getSubcategories().addAll(category.getSubcategories());
            }
        } else {
            for (Category category : categories) {
                chat.getSubcategories().removeAll(category.getSubcategories());
            }
        }

        chatService.save(chat);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id %s was not found", categoryId)));
    }
}
