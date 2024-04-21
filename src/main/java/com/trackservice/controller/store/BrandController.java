package com.trackservice.controller.store;

import com.trackservice.dto.store.BrandDto;
import com.trackservice.service.BrandService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {

    private ModelMapper modelMapper;
    private BrandService brandService;

    @PostMapping
    public void addBrand(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Map<String, String> body, Model model) {
        BrandDto brand = modelMapper.map(body, BrandDto.class);
        try{
            brandService.saveBrand(brand , userDetails.getUsername());
            model.addAttribute("msg", "Brand added successfully");
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
        }
    }

    @PatchMapping
    public void updateBrand() {
    }

    @GetMapping("/{id}")
    public void getBrand(@PathVariable Long id) {
    }

    @GetMapping("/{id}/stores")
    public void getStoresByBrand(@PathVariable Long id) {

    }
}
