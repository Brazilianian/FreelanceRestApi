package com.mitit.repository;

import com.mitit.domain.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProposalRepo extends JpaRepository<Proposal, Integer> {
    List<Proposal> findAllByDateAfter(LocalDateTime date);

}
