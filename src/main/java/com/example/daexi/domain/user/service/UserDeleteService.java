package com.example.daexi.domain.user.service;

import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserDeletionFailedException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class UserDeleteService {
    private final UserRepository userRepository;

    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        userRepository.deleteById(id);
        if(userRepository.existsById(id)) {
            throw new UserDeletionFailedException();
        }
    }
}
