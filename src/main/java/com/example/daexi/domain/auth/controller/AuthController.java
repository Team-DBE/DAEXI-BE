package com.example.daexi.domain.auth.controller;

import com.example.daexi.domain.auth.dto.request.LogOutRequestDto;
import com.example.daexi.domain.auth.dto.request.ReissueRequestDto;
import com.example.daexi.domain.auth.dto.request.SignInRequestDto;
import com.example.daexi.domain.auth.dto.request.SignUpRequestDto;
import com.example.daexi.domain.auth.dto.response.JwtTokenResponse;
import com.example.daexi.domain.auth.service.LogOutService;
import com.example.daexi.domain.auth.service.ReissueTokenService;
import com.example.daexi.domain.auth.service.SignInService;
import com.example.daexi.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignInService signInService;
    private final SignUpService signUpService;
    private final LogOutService logOutService;
    private final ReissueTokenService reissueTokenService;


    @PostMapping("/signup")
    public ResponseEntity<JwtTokenResponse> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpService.signUp(signUpRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtTokenResponse> signin(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok().body(signInService.signIn(signInRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto logOutRequestDto) {
        logOutService.logOut(logOutRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<JwtTokenResponse> reissueToken(@RequestBody ReissueRequestDto reissueRequestDto) {
        return ResponseEntity.ok().body(reissueTokenService.reissue(reissueRequestDto));
    }
}
