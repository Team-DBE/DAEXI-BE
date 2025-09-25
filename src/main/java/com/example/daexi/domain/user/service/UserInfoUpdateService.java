package com.example.daexi.domain.user.service;

import com.example.daexi.domain.user.dto.request.UserInfoUpdateRequestDto;
import com.example.daexi.domain.user.dto.response.UserDetailResponseDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoUpdateService {
    private final UserRepository userRepository;

    public UserDetailResponseDto UserDetailUpdate(UserInfoUpdateRequestDto dto){
        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.updateDetail(dto.getUserName(), dto.getAccountNumber(), dto.getUserDetail(), dto.getAccountId());
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .userName(user.getUserName())
                .accountId(user.getAccountId())
                .accountNumber(user.getAccountNumber())
                .userDetail(user.getUserDetail()).build();
    }
}
