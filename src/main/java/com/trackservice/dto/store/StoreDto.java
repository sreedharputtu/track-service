package com.trackservice.dto.store;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trackservice.dto.address.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class StoreDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("address")
    private AddressDto address;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("status")
    private String status;
    @JsonProperty("contact_phone")
    private String contactPhone;
    @JsonProperty("contact_email")
    private String contactEmail;
    @JsonProperty("contact_person_name")
    private String contactPersonName;
    @JsonProperty("registration_type")
    private String registrationType;
    @JsonProperty("registration_number")
    private String registrationNumber;
    @JsonProperty("metadata")
    private String metadata;
    @JsonProperty("brand")
    private BrandDto brand;
    @JsonProperty("store_type")
    private String storeType;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
}
