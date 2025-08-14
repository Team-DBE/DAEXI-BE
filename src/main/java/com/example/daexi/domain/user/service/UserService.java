package com.example.daexi.domain.user.service;

import com.example.daexi.domain.daexiParty.entity.Party;
import com.example.daexi.domain.daexiParty.entity.repository.PartyRepository;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.entity.repository.UserRepository;
import com.example.daexi.domain.user.presentation.dto.UserJoinPartyRequestDto;
import com.example.daexi.domain.user.presentation.dto.UserJoinPartyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PartyRepository partyRepository;

    public User getUserById(Long userId, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.builder()
                .party(party)
                .build();

        return userRepository.save(user);
    }

    public User joinParty(UserJoinPartyResponseDto userJoinPartyResponseDto, User user) {
        userJoinPartyResponseDto.builder()
                .userId(user.getId())
                .accountNumber(user.getAccountNumber())
                .userNumber(Long.toString(user.getUserNumber()))
                .username(user.getUserName())
                .build();
        return userRepository.save(user);
    }
}
