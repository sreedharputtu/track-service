package com.trackservice.service.catalog;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.dto.catalog.CategoryDto;
import com.trackservice.entity.product.Catalog;
import com.trackservice.entity.product.Category;
import com.trackservice.entity.product.Product;
import com.trackservice.repository.catalog.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CatalogService {
    private CatalogRepository catalogRepository;
    public CatalogDto getCatalogs(long storeID) {
        List<Catalog> catalogs = catalogRepository.findAllByStoreId(storeID);
        Catalog catalog = catalogs.get(0);
         CatalogDto catalogDto  = CatalogDto.builder().
                    id(catalog.getId()).
                    name(catalog.getName()).
                    description(catalog.getDescription()).
                    active(catalog.isActive()).
                 build();
         return catalogDto;
    }

    public void saveCategory(CategoryDto categoryDto, Long catalogId , String selectedProductIds) {

        if(selectedProductIds== null || selectedProductIds.isEmpty()){
            throw new RuntimeException("No products selected");
        }

        Catalog catalog = catalogRepository.findById(catalogId).
                orElseThrow(() -> new RuntimeException("Catalog not found"));
        Category category = Category.builder().
                name(categoryDto.getName()).
                description(categoryDto.getDescription()).
                active(true).
                products(getSelectedProducts(selectedProductIds)).
                createdAt(new Date()).
                build();

        if(catalog.getCategories() == null){
            catalog.setCategories(new HashSet<>());
        }
        catalog.getCategories().add(category);
        catalogRepository.save(catalog);
    }

    private Set<Product> getSelectedProducts(String productIdsStr){
        String[] productIds = productIdsStr.split(",");
        Set<Product> selectedProducts = new HashSet<>();
        for (int i = 0; i < productIds.length; i++) {
            selectedProducts.add(Product.builder().id(Long.parseLong(productIds[i])).build());
        }
        return selectedProducts;
    }
}
