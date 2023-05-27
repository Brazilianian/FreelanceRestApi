package com.mitit.service;

import com.mitit.domain.Proposal;
import com.mitit.repository.ProposalRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProposalService {
    private final ProposalRepo proposalRepo;

    public List<Proposal> getProposalNewerThan(LocalDateTime localDateTime) {
        return proposalRepo.findAllByDateAfter(localDateTime);
    }
}
