package com.mitit.mapper;

import com.mitit.domain.Proposal;
import com.mitit.dto.ProposalDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProposalMapper {
    public List<ProposalDto> fromObjectListToDtoList(List<Proposal> proposals) {
        List<ProposalDto> proposalDtoList = new ArrayList<>();
        for (Proposal proposal : proposals) {
            proposalDtoList.add(ProposalDto.of(proposal));
        }
        return proposalDtoList;
    }
}
