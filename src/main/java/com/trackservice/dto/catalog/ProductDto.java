package com.trackservice.dto.catalog;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ProductDto {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private double price;
    @JsonProperty("brandId")
    private long brandId;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("stock")
    private Integer stock;
    @JsonProperty("groups")
    private List<ModifierGroupDto> modifierGroups;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}
