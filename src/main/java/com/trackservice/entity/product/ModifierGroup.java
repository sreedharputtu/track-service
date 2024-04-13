package com.trackservice.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "modifier_groups")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifierGroup {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean required;
    private int min;
    private int max;
    private Long brandId;
    @ManyToMany
    Set<ModifierOption> modifierOptions;
}
