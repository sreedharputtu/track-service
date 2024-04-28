package com.trackservice.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private Double price;
    private Long brandId;
    private String imageUrl;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ModifierGroup> modifierGroups;
    private Integer stock;
    private Date createdAt;
    private Date updatedAt;
}
