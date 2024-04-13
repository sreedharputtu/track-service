package com.trackservice.dto;

import com.trackservice.dto.address.AddressDto;

import java.util.List;

public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private Integer[] scopedIds;
    private boolean active;
    private List<AddressDto> address;
}
