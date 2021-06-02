package com.grap.user.service;

import com.grap.membership.repository.MembershipRepository;
import com.grap.user.domain.User;
import com.grap.user.dto.UserInfoResponseDto;
import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    @Transactional
    public UserResponseDto saveOrUpdate(UserSaveRequestDto requestDto) {

        return userRepository.findByEmail(requestDto.getEmail())
                .map(user -> new UserResponseDto(user.update(requestDto.getName(), requestDto.getPicture()), true))
                .orElseGet(() -> new UserResponseDto(userRepository.save(requestDto.toEntity()), false));
    }

    @Transactional
    public Map<String, Boolean> dupCheckNickname(String nickname) {

        HashMap<String, Boolean> map = new HashMap<>();

        map.put("isDup", userRepository.findByNickname(nickname).isPresent());

        return map;
    }

    @Transactional
    public Long saveNickname(Long userId, String nickname) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        user.update(nickname);

        return userId;
    }

    @Transactional(readOnly = true)
    public Long countAll() {

        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public List<UserInfoResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserInfoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> findAllNames() {

        return userRepository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public MembershipResponseDto findByUserId(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
//        );
//
//        if(user)
//    }

    @Transactional
    public Long delete(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        userRepository.delete(user);

        return user.getId();
    }

    @Transactional(readOnly = true)
    public UserInfoResponseDto findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        return new UserInfoResponseDto(user);
    }
}