package com.trackservice.controller.catalog;

import com.trackservice.dto.catalog.ProductDto;
import com.trackservice.dto.store.StoreDto;
import com.trackservice.entity.product.Product;
import com.trackservice.service.product.ProductService;
import com.trackservice.util.Validator;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ModelMapper modelMapper;
    private ProductService productService;

    @RequestMapping("/list")
    public String getProducts(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<ProductDto> products = new ArrayList<>();
        try {
            Long brandId = Long.parseLong(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
            products = productService.getProductByBrandId(brandId);
            model.addAttribute("products", productService.getProductByBrandId(brandId));
            return "html/products.html";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            log.error("Error while fetching products: {}", e.getMessage());
            return "html/error.html";
        }
    }

    @RequestMapping("/{id}")
    public String getProductById(Long id) {
        return "html/product.html";
    }

    @RequestMapping("/page/create")
    public String createProduct() {
        return "html/product.html";
    }

    @PostMapping
    public String saveProduct(@AuthenticationPrincipal UserDetails userDetails,@RequestParam Map<String,String> body ,Model model) {
        ProductDto product = modelMapper.map(body, ProductDto.class);
        try {
            Validator.isSaveProductValid(body);
            Long brandId = Long.parseLong(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
            productService.saveProduct(product, brandId);
            model.addAttribute("msg","Product created successfully");
            return "html/success.html";
        } catch (Exception e) {
            log.error("Error while creating product: {}", e.getMessage());
            model.addAttribute("msg","Error while creating product");
            return "html/error.html";
        }
    }
}
