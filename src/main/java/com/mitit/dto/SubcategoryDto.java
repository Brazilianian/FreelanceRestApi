package com.mitit.dto;

import com.mitit.domain.Subcategory;
import lombok.Getter;

@Getter
public class SubcategoryDto {
    private final Long id;
    private final String name;
    private final CategoryDto category;


    private SubcategoryDto(Long id, String name, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static SubcategoryDto from(Subcategory subcategory) {
        return new SubcategoryDto(subcategory.getId(),
                subcategory.getName(),
                CategoryDto.from(subcategory.getCategory()));
    }
}
