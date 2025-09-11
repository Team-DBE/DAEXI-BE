package com.example.daexi.domain.auth.service;

import com.example.daexi.domain.auth.dto.request.SignUpRequestDto;
import com.example.daexi.domain.auth.dto.response.JwtTokenResponse;
import com.example.daexi.domain.auth.exception.AccountIdAlreadyExistsException;
import com.example.daexi.domain.auth.exception.UserNumberAlreadyExistsException;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtTokenResponse signUp(SignUpRequestDto signUpRequestDto) {
        if(userRepository.existsByAccountId(signUpRequestDto.getAccountId()))
            throw new AccountIdAlreadyExistsException();
        else if(userRepository.existsByUserNumber(signUpRequestDto.getUserNumber()))
            throw new UserNumberAlreadyExistsException();


        User user = User.builder()
                .accountId(signUpRequestDto.getAccountId())
                .userName(signUpRequestDto.getUserName())
                .accountNumber(signUpRequestDto.getAccountNumber())
                .userNumber(signUpRequestDto.getUserNumber())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
        userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(signUpRequestDto.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(signUpRequestDto.getAccountId());
        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
