package com.example.daexi.domain.user.service;

import com.example.daexi.domain.user.dto.response.UserDetailResponseDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetDetailService {
    private final UserRepository userRepository;

    public UserDetailResponseDto getUserDetail(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserDetailResponseDto.builder()
                .userName(user.getUserName())
                .userDetail(user.getUserDetail())
                .accountNumber(user.getAccountNumber())
                .accountId(user.getAccountId())
                .build();
    }
}
