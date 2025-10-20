package com.example.daexi.domain.user.service;

import com.example.daexi.domain.user.dto.request.UserInfoUpdateRequestDto;
import com.example.daexi.domain.user.dto.response.UserDetailResponseDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoUpdateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailResponseDto UserDetailUpdate(Long id, UserInfoUpdateRequestDto dto){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

        user.updateDetail(dto.getUserName(),
                dto.getAccountNumber(),
                dto.getUserDetail(),
                dto.getAccountId(),
                passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

        return UserDetailResponseDto.builder()
                .userName(user.getUserName())
                .accountId(user.getAccountId())
                .accountNumber(user.getAccountNumber())
                .userDetail(user.getUserDetail()).build();
    }
}
