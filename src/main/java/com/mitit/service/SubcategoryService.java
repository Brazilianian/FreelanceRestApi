package com.mitit.service;

import com.mitit.domain.Subcategory;
import com.mitit.repository.SubcategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    private final SubcategoryRepo subcategoryRepo;

    public List<Subcategory> getSubcategoriesByFreelanceSiteNameAndChatId(String freelanceSiteName, Long chatId) {
        return subcategoryRepo.findByCategory_FreelanceSite_NameAndChats_ChatId(freelanceSiteName, chatId);
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepo.findByCategory_Id(categoryId);
    }

    public List<Subcategory> getSubcategoriesByChatId(Long chatId) {
        return subcategoryRepo.findByChats_ChatId(chatId);
    }

    public Subcategory getSubcategoryById(Long subcategoryId) {
        return subcategoryRepo.findById(subcategoryId)
                .orElse(null);
    }
}
