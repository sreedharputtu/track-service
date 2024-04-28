package com.trackservice.controller.catalog;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.dto.catalog.CategoryDto;
import com.trackservice.dto.catalog.ProductDto;
import com.trackservice.service.catalog.CatalogService;
import com.trackservice.service.product.ProductService;
import com.trackservice.util.Validator;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/catalogs")
public class CatalogController {
    private CatalogService catalogService;
    private ProductService productService;
    private ModelMapper modelMapper;

    @RequestMapping("/{storeId}")
    public String getCatalogByStoreId(@PathParam("storeId") String storeIdParam, Model model) {
        Long storeId = 1L;
        log.info("Getting catalog by storeId: {}", storeId);
        model.addAttribute("storeId", storeId);
        CatalogDto catalog = catalogService.getCatalogs(storeId);
        model.addAttribute("catalog", catalog);
        log.info("Catalog: {}", catalog);
        return "html/catalog.html";
    }

    @RequestMapping("/{catalogId}/categories/page/create")
    public String getCatalogsByStoreId(@AuthenticationPrincipal UserDetails userDetails, @PathParam("catalogId") Long catalogId, Model model) {
        catalogId = 1L;
        Long brandId = Long.parseLong(userDetails.getAuthorities().stream().findFirst().get().getAuthority());
        List<ProductDto> products = productService.getProductByBrandId(brandId);
        model.addAttribute("products", products);
        model.addAttribute("catalogId", catalogId);
        return "html/category.html";
    }

    @PostMapping("/{catalogId}/categories")
    public String saveCategory(@PathParam("catalogId") Long catalogId, @RequestParam("selectedProducts") String selectedProducts , @RequestParam Map<String,String> body) {
        log.info("Saving category: {}", body);
        log.info("Selected products: {}", selectedProducts);
        catalogId = 1L;
        CategoryDto categoryDto = modelMapper.map(body, CategoryDto.class);
        catalogService.saveCategory(categoryDto , catalogId , selectedProducts);
        return "html/category.html";
    }

}
