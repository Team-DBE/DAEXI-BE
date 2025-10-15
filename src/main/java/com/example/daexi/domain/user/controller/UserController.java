package com.example.daexi.domain.user.controller;

import com.example.daexi.domain.user.dto.request.UserInfoUpdateRequestDto;
import com.example.daexi.domain.user.dto.response.UserDetailResponseDto;
import com.example.daexi.domain.user.service.UserDeleteService;
import com.example.daexi.domain.user.service.UserGetDetailService;
import com.example.daexi.domain.user.service.UserInfoUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserGetDetailService userGetDetailService;
    private final UserDeleteService userDeleteService;
    private final UserInfoUpdateService userInfoUpdateService;

    @GetMapping("{id}")
    public ResponseEntity<UserDetailResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userGetDetailService.getUserDetail(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userDeleteService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDetailResponseDto> updateUserInfo(@PathVariable Long id, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        return ResponseEntity.ok().body(userInfoUpdateService.UserDetailUpdate(userInfoUpdateRequestDto));
    }
}
