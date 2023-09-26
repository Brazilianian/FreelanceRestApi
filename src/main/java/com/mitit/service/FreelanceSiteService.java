package com.mitit.service;

import com.mitit.domain.FreelanceSite;
import com.mitit.repository.FreelanceSiteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreelanceSiteService {

    private final FreelanceSiteRepo freelanceSiteRepo;

    public List<FreelanceSite> getFreelanceSites() {
        return freelanceSiteRepo.findAll();
    }
}
