package com.trackservice.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "modifier_options")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifierOption {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long brandId;
    private String imageUrl;
}
