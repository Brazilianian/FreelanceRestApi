package com.mitit.repository;

import com.mitit.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAllByFreelanceSite_Name(String freelanceSite_name);
}
