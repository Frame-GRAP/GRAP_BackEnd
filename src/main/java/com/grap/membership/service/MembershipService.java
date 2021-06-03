package com.grap.membership.service;

import com.grap.membership.domain.Membership;
import com.grap.membership.dto.MembershipResponseDto;
import com.grap.membership.dto.MembershipSaveRequestDto;
import com.grap.membership.dto.MembershipUpdateRequestDto;
import com.grap.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Transactional
    public Long save(MembershipSaveRequestDto requestDto) {
        Membership membership = requestDto.toEntity();

        return membershipRepository.save(membership).getId();
    }
    @Transactional
    public List<MembershipResponseDto> findAllMembership() {

        return membershipRepository.findAll().stream()
                .map(MembershipResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long membershipId, MembershipUpdateRequestDto requestDto) {

        Membership membership = membershipRepository.findById(membershipId).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버십 존재하지 않습니다.")
        );

        membership.update(requestDto.getName(), requestDto.getPrice(), requestDto.getCouponNum());

        return membershipId;
    }
}
