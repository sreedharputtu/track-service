package com.trackservice.service.catalog;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.entity.product.Catalog;
import com.trackservice.repository.catalog.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogService {
    private CatalogRepository catalogRepository;
    public List<CatalogDto> getCatalogs(long storeID) {
        List<CatalogDto> catalogDtos = new ArrayList<>();
        List<Catalog> catalogs = catalogRepository.findAllByStoreId(storeID);
        for (Catalog catalog : catalogs){
         CatalogDto catalogDto  = CatalogDto.builder().
                    id(catalog.getId()).
                    name(catalog.getName()).
                    description(catalog.getDescription()).
                    active(catalog.isActive()).
                 build();
            catalogDtos.add(catalogDto);
        }
        return catalogDtos;
    }
}
