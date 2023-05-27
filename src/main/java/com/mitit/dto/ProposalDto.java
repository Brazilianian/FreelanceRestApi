package com.mitit.dto;

import com.mitit.domain.Proposal;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ProposalDto {
    private int id;

    private String title;
    private String price;
    private LocalDateTime posted_date;
    private String description;
    private String link;
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

    public static ProposalDto of(Proposal proposal) {
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
