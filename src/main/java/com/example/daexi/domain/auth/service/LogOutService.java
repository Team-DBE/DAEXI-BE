package com.example.daexi.domain.auth.service;

import com.example.daexi.domain.auth.dto.request.LogOutRequestDto;
import com.example.daexi.domain.auth.exception.AccountNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogOutService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void logOut(LogOutRequestDto logOutRequestDto) {
        if(!userRepository.existsByAccountId(logOutRequestDto.getAccountId()))
            throw new AccountNotFoundException();
        else
            jwtTokenProvider.deleteRefreshToken(logOutRequestDto.getAccountId());
    }
}
