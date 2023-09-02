package com.mitit.mapper;

import com.mitit.domain.Proposal;
import com.mitit.dto.ProposalDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProposalMapper {

    private final FreelanceSiteMapper freelanceSiteMapper;
    private final SubcategoryMapper subcategoryMapper;

    public List<ProposalDto> fromObjectListToDtoList(List<Proposal> proposals) {
        List<ProposalDto> proposalDtoList = new ArrayList<>();
        for (Proposal proposal : proposals) {
            ProposalDto proposalDto = ProposalDto.from(proposal);

            proposalDto.setFreelance_site(freelanceSiteMapper.fromObjectToDto(proposal.getFreelanceSite()));
            proposalDto.getSubcategories().addAll(
                    proposal.getSubcategories()
                            .stream()
                            .map(subcategoryMapper::fromObjectToDto)
                            .toList()
            );

            proposalDtoList.add(proposalDto);
        }
        return proposalDtoList;
    }
}
