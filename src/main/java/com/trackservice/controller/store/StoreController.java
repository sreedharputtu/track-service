package com.trackservice.controller.store;

import com.trackservice.dto.store.StoreDto;
import com.trackservice.service.store.StoreService;
import com.trackservice.util.Validator;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public String addStore(@RequestParam Map<String,String> body , Model model) {
        log.info("Request Body: {}", body);
        try{
            Validator.isSaveStoreValid(body);
            StoreDto dto = modelMapper.map(body, StoreDto.class,"store");
            convertAddressDto(dto,body);
            log.info("StoreDto: {}", dto);
            model.addAttribute("msg","Store created successfully");
            storeService.addStore(dto);
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
    public String getStores(@PathParam(value = "page") Integer page, Model model) {
        log.info("Page: {}", page);
        if (page == null) {
            page = 1;
        }
        log.info("Page: {}", page);
        List<StoreDto> stores = storeService.getAllStores(page-1,DEFAULT_PAGE_SIZE);
        log.info("Stores: {}", stores);
        model.addAttribute("stores",stores);
        model.addAttribute("prev_page",page-1);
        model.addAttribute("next_page",page+1);
        return "html/store_list.html";
    }
}
