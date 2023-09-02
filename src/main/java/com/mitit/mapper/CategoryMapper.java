package com.mitit.mapper;

import com.mitit.domain.Category;
import com.mitit.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDto fromObjectToJson(Category category) {
        return CategoryDto.from(category);
    }
}
