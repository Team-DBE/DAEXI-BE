package com.example.daexi.domain.auth.service;

import com.example.daexi.domain.auth.dto.request.SignInRequestDto;
import com.example.daexi.domain.auth.dto.request.SignUpRequestDto;
import com.example.daexi.domain.auth.dto.response.JwtTokenResponse;
import com.example.daexi.domain.auth.exception.AccountNotFoundException;
import com.example.daexi.domain.auth.exception.InvalidPasswordException;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenResponse signIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findByAccountId(signInRequestDto.getAccountId()).orElseThrow(() -> new AccountNotFoundException());

        if(!passwordEncoder.matches(signInRequestDto.getPassword(), user.getPassword()))
            throw new InvalidPasswordException();

        String accessToken = jwtTokenProvider.generateAccessToken(signInRequestDto.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(signInRequestDto.getAccountId());

        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();
    }
}
