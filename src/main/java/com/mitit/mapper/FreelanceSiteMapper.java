package com.mitit.mapper;

import com.mitit.domain.FreelanceSite;
import com.mitit.dto.FreelanceSiteDto;
import org.springframework.stereotype.Service;

@Service
public class FreelanceSiteMapper {
    public FreelanceSiteDto fromObjectToDto(FreelanceSite freelanceSite) {
        return FreelanceSiteDto.from(freelanceSite);
    }
}
