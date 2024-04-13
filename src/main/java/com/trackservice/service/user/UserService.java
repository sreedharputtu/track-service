package com.trackservice.service.user;

import com.trackservice.entity.user.User;
import com.trackservice.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
