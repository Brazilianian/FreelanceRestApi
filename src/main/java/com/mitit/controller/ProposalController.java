package com.mitit.controller;

import com.mitit.domain.Proposal;
import com.mitit.dto.ProposalDto;
import com.mitit.mapper.ProposalMapper;
import com.mitit.service.ProposalService;
import com.mitit.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/proposals")
public class ProposalController {

    private final DateUtil dateUtil;
    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;

    @GetMapping
    @ResponseBody
    public List<ProposalDto> getProposalNewerThan(@RequestParam String date) {
        LocalDateTime localDateTime = dateUtil.parseDateByPattern(date, "yyyy-MM-dd HH:mm:ss");
        List<Proposal> proposals = proposalService.getProposalNewerThan(localDateTime);
        return proposalMapper.fromObjectListToDtoList(proposals);
    }
}
