package com.trackservice.controller.store;

import com.trackservice.dto.store.StoreDto;
import com.trackservice.service.store.StoreService;
import com.trackservice.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/stores")
public class StoreController {

    private final int DEFAULT_PAGE_SIZE = 5;
    private final int DEFAULT_PAGE = 0;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping("/page/create")
    public String createStorePage() {
        return "html/create_store.html";
    }

    @Validated
    @PostMapping
    public String addStore(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Map<String,String> body , Model model) {
        log.info("Request Body: {}", body);
        try{
            Validator.isSaveStoreValid(body);
            StoreDto dto = modelMapper.map(body, StoreDto.class,"store");
            convertAddressDto(dto,body);
            log.info("StoreDto: {}", dto);
            Long brandId = Long.parseLong(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
            storeService.addStore(dto , brandId);
            model.addAttribute("msg","Store created successfully");
        } catch (Exception e) {
            log.error("Error while creating store: {}", e.getMessage());
            model.addAttribute("msg","Error while creating store");
            return "html/error.html";
        }
        return "html/success.html";
    }

    private void  convertAddressDto(StoreDto dto , Map<String,String> body) {
        dto.getAddress().setStreet(body.get("street"));
        dto.getAddress().setCity(body.get("city"));
        dto.getAddress().setState(body.get("state"));
        dto.getAddress().setCountry(body.get("country"));
        dto.getAddress().setZipCode(body.get("zipCode"));
    }

    @PatchMapping
    public void updateStore(@RequestParam Map<String,String> body , Model model) {
    }

    @GetMapping("/{id}")
    public String getStore(@PathVariable Long id , Model model) {
        log.info("Store ID: {}", id);
        StoreDto storeDto = storeService.getStoreById(id);
        model.addAttribute("store",storeDto);
        return "html/store.html";
    }

    @GetMapping("/list")
    public String getStores(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<StoreDto> stores = new ArrayList<>();
        Long brandId = Long.parseLong(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
        stores = storeService.getAllStoresByBrandId(brandId);
        log.info("Stores: {}", stores);
        model.addAttribute("stores",stores);
        return "html/store_list.html";
    }
}
