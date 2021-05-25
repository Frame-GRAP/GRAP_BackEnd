package com.grap.membership.controller;

import com.grap.membership.dto.MembershipResponseDto;
import com.grap.membership.dto.MembershipSaveRequestDto;
import com.grap.membership.dto.MembershipUpdateRequestDto;
import com.grap.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class MembershipApiController {

    private final MembershipService membershipService;

    @GetMapping("/api/membership/all")
    public List<MembershipResponseDto> findAll() {

        return membershipService.findAllMembership();
    }

    @PostMapping("api/membership")
    public Long saveMembership(@RequestBody MembershipSaveRequestDto requestDto) {

        return membershipService.save(requestDto);
    }

    @PutMapping("/api/membership/{membershipId}")
    public Long updateMembership(@PathVariable Long membershipId, @RequestBody MembershipUpdateRequestDto requestDto) {

        return membershipService.update(membershipId, requestDto);
    }
}
