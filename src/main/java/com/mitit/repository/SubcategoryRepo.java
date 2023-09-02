package com.mitit.repository;

import com.mitit.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findByChats_ChatId(Long chatId);
    List<Subcategory> findByCategory_Id(Long id);
    List<Subcategory> findByCategory_FreelanceSite_NameAndChats_ChatId(String name, Long chatId);

}
