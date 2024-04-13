package com.trackservice.controller.store;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/brands")
public class BrandController {
    @PostMapping
    public void addBrand(@PathVariable Map<String, String> body, Model model) {

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
