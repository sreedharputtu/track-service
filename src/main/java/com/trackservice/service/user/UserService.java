package com.trackservice.service.user;

import com.trackservice.entity.user.User;
import com.trackservice.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = StringUtils.delete(username, " ");
        User user = userRepository.findByUsername(username);
        log.info("Username:{}", username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Long scopedId : user.getScopedIds()) {
            authorities.add(new SimpleGrantedAuthority("" + scopedId));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities
        );
    }
}
