package com.trackservice.controller.catalog;

import com.trackservice.dto.catalog.ProductDto;
import com.trackservice.service.product.ProductService;
import com.trackservice.util.Validator;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @RequestMapping("/list")
    public String getProducts(@PathParam("brandId") Long brandId , Model model) {
        //Validator.isStoreIdValid(brandId);
        log.info("Store ID: {}", brandId);
        List<ProductDto> products = new ArrayList<>();
        try {
            products = productService.getProductByBrandId(brandId);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            log.error("Error while fetching products: {}", e.getMessage());
            return "html/error.html";
        }
        model.addAttribute("products", productService.getProductByBrandId(brandId));
        return "html/product_list.html";
    }

    @RequestMapping("/{id}")
    public String getProductById(Long id) {
        return "html/product.html";
    }

    @RequestMapping("/page/create")
    public String createProduct() {
        return "html/product.html";
    }
}
