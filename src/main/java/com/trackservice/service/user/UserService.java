package com.trackservice.service.user;

import com.trackservice.dto.UserDto;
import com.trackservice.entity.store.Brand;
import com.trackservice.entity.user.User;
import com.trackservice.repository.brand.BrandRepository;
import com.trackservice.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BrandRepository brandRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = StringUtils.delete(username, " ");
        User user = userRepository.findByUsername(username);
        log.info("Username:{}", username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.getScopedIds() != null) {
            for (Long scopedId : user.getScopedIds()) {
                authorities.add(new SimpleGrantedAuthority("" + scopedId));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities
        );
    }

    public void saveUser(UserDto userDto) {
        Brand defaultBrand = Brand.builder()
                .name("DefaultBrand_"+userDto.getUsername())
                .active(true)
                .createdAt(new Date())
                .build();
        Brand savedBrand = brandRepository.save(defaultBrand);
        Set<Long> scopedIds = new HashSet<>();
        scopedIds.add(savedBrand.getId());
        User user = User.builder()
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .active(false)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .scopedIds(scopedIds)
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);

    }
}
