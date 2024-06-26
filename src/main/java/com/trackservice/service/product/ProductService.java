package com.trackservice.service.product;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.dto.catalog.ProductDto;
import com.trackservice.entity.product.Catalog;
import com.trackservice.entity.product.Product;
import com.trackservice.entity.store.Store;
import com.trackservice.repository.catalog.CatalogRepository;
import com.trackservice.repository.product.ProductRepository;
import com.trackservice.repository.store.StoreRepository;
import com.trackservice.util.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CatalogRepository catalogRepository;
    private final StoreRepository storeRepository;

    public void createCatalogue(CatalogDto catalogDto, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        Catalog catalog = Catalog.builder()
                .name(catalogDto.getName())
                .description(catalogDto.getDescription())
                .active(catalogDto.isActive())
                .createdAt(new Date())
                .updatedAt(new Date())
                .store(store)
                .build();
        catalogRepository.save(catalog);
    }

    public List<ProductDto> getProductByBrandId(Long brandId)  {
        List<Product> products = productRepository.findByBrandId(brandId);
        return convertProductToDto(products);
    }

    public void saveProduct(ProductDto productDto,Long brandId) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl())
                .stock(productDto.getStock())
                .active(productDto.isActive())
                .brandId(brandId)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        productRepository.save(product);
    }

    public List<ProductDto> convertProductToDto(List<Product> products) {
       List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .stock(product.getStock())
                    .active(product.isActive())
                    .brandId(product.getBrandId())
                    .createdAt(CommonUtils.convertDateToString(product.getCreatedAt()))
                    .updatedAt(CommonUtils.convertDateToString(product.getUpdatedAt()))
                    .build();
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
