package com.trackservice.entity.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "brands")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
