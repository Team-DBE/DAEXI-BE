package com.example.daexi.domain.auth.controller;

import com.example.daexi.domain.auth.dto.request.SignInRequestDto;
import com.example.daexi.domain.auth.dto.request.SignUpRequestDto;
import com.example.daexi.domain.auth.dto.response.JwtTokenResponse;
import com.example.daexi.domain.auth.service.SignInService;
import com.example.daexi.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignInService signInService;
    private final SignUpService signUpService;


    @PostMapping("/signup")
    public ResponseEntity<JwtTokenResponse> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpService.signUp(signUpRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtTokenResponse> signin(@RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok().body(signInService.signIn(signInRequestDto));
    }
}
