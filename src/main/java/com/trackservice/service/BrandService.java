package com.trackservice.service;

import com.trackservice.dto.store.BrandDto;
import com.trackservice.entity.store.Brand;
import com.trackservice.entity.user.User;
import com.trackservice.repository.brand.BrandRepository;
import com.trackservice.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public void saveBrand(BrandDto brandDto, String username) {
        Brand brand = Brand.builder()
                .name(brandDto.getName())
                .description(brandDto.getDescription())
                .active(true)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        Brand savedBrand = brandRepository.save(brand);
        User user = userRepository.findByUsername(username);
        user.getScopedIds().add(savedBrand.getId());
        userRepository.save(user);
    }


}
