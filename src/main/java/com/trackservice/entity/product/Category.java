package com.trackservice.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean active;
    private Long brandId;
    @ManyToMany(fetch = FetchType.LAZY)
    Set<Product> products;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    private Catalog catalog;
}
