package com.trackservice.service.catalog;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.dto.catalog.CategoryDto;
import com.trackservice.dto.catalog.ProductDto;
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
        List<CategoryDto> categories = new ArrayList<>();
        for (Category category : catalog.getCategories()) {
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : category.getProducts()) {
                ProductDto productDto = ProductDto.builder().
                        id(product.getId()).
                        name(product.getName()).
                        description(product.getDescription()).
                        price(product.getPrice()).
                        active(product.isActive()).
                        build();
                productDtos.add(productDto);
            }
            CategoryDto categoryDto = CategoryDto.builder().
                    id(category.getId()).
                    name(category.getName()).
                    description(category.getDescription()).
                    products(productDtos).
                    active(category.isActive()).
                    build();
            categories.add(categoryDto);
        }
         CatalogDto catalogDto  = CatalogDto.builder().
                    id(catalog.getId()).
                    name(catalog.getName()).
                    description(catalog.getDescription()).
                    active(catalog.isActive()).
                    categories(categories).
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
