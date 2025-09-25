package com.example.daexi.domain.auth.service;

import com.example.daexi.domain.auth.dto.request.ReissueRequestDto;
import com.example.daexi.domain.auth.dto.response.JwtTokenResponse;
import com.example.daexi.domain.auth.exception.AccountNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueTokenService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenResponse reissue(ReissueRequestDto reissueRequestDto) {
        if(!userRepository.existsByAccountId(reissueRequestDto.getAccountId())){
            throw new AccountNotFoundException();
        }
        String accessToken = jwtTokenProvider.reissueAccessToken(reissueRequestDto.getRefreshToken(), reissueRequestDto.getAccountId());
        return new JwtTokenResponse(accessToken,reissueRequestDto.getRefreshToken());
    }
}
