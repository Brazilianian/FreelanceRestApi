package com.mitit.mapper;

import com.mitit.domain.Subcategory;
import com.mitit.dto.SubcategoryDto;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryMapper {
    public SubcategoryDto fromObjectToDto(Subcategory subcategory) {
        return SubcategoryDto.from(subcategory);
    }
}
