package com.trackservice.entity.store;

import com.trackservice.entity.address.Address;
import com.trackservice.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "stores")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL,targetEntity = Address.class, optional = false)
    private Address address;
    private boolean active;
    private String contactPhone;
    private String contactEmail;
    private String contactPersonName;
    private String registrationType;
    private String registrationNumber;
    private String metadata;
    @ManyToOne(targetEntity = Brand.class ,optional = true)
    private Brand brand;
    private String storeType;
    private Date createdAt;
    private Date updatedAt;
    private String status;

}

