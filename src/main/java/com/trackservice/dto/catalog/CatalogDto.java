package com.trackservice.dto.catalog;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.trackservice.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class CatalogDto {
    private long id;
    private String name;
    private String description;
    private boolean active;
    private List<CategoryDto> categories;
}
