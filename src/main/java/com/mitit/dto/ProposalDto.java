package com.mitit.dto;

import com.mitit.domain.Proposal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ProposalDto {
    private final int id;

    private final String title;
    private final String price;
    private final LocalDateTime posted_date;
    private final String description;
    private final String link;
    private final List<SubcategoryDto> subcategories = new ArrayList<>();

    @Setter
    private FreelanceSiteDto freelance_site;

    private List<String> additional_info_tags = new ArrayList<>();

    private void setAdditional_info_tags(List<String> additional_info_tags) {
        this.additional_info_tags = additional_info_tags;
    }

    private ProposalDto(int id, String title, String price, LocalDateTime posted_date, String description, String link) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.posted_date = posted_date;
        this.description = description;
        this.link = link;
    }

    public static ProposalDto from(Proposal proposal) {
        ProposalDto proposalDto = new ProposalDto(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getPrice(),
                proposal.getDate(),
                proposal.getDescription(),
                proposal.getLink()
        );

        proposalDto.setAdditional_info_tags(Arrays.stream(proposal.getAdditional_info_tags().split( ",")).toList());


        return proposalDto;
    }
}
