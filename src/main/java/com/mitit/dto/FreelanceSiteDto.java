package com.mitit.dto;

import com.mitit.domain.FreelanceSite;
import lombok.Getter;

@Getter
public class FreelanceSiteDto {
    private final String name;
    private final String link;

    private FreelanceSiteDto(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public static FreelanceSiteDto from(FreelanceSite freelanceSite) {

        return new FreelanceSiteDto(
                freelanceSite.getName(),
                freelanceSite.getLink()
        );
    }
}
