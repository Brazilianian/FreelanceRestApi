package com.mitit.dto;

import com.mitit.domain.Category;
import lombok.Getter;

@Getter
public class CategoryDto {
    private final Long id;
    private final String name;
    private final FreelanceSiteDto freelance_site;


    private CategoryDto(Long id, String name, FreelanceSiteDto freelanceSiteDto) {
        this.id = id;
        this.name = name;
        this.freelance_site = freelanceSiteDto;
    }

    public static CategoryDto from(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                FreelanceSiteDto.from(category.getFreelanceSite())
        );
    }
}
