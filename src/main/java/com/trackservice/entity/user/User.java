package com.trackservice.entity.user;

import com.trackservice.entity.address.Address;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    private String phone;
    @OneToMany()
    private Set<Address> address;
    private boolean active;
    private String role;
    private String firstName;
    private String lastName;
    private Integer[] scopedIds;
}
