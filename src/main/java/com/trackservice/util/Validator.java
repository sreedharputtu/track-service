package com.trackservice.util;


import io.micrometer.common.util.StringUtils;

import java.util.Map;

/**
 * private String name;
 *
 * @JsonProperty("address") private AddressDto address;
 * @JsonProperty("contact_phone") private String contactPhone;
 * @JsonProperty("contact_email") private String contactEmail;
 * @JsonProperty("contact_person_name") private String contactPersonName;
 * @JsonProperty("registration_type") private String registrationType;
 * @JsonProperty("registration_number") private String registrationNumber;
 * @JsonProperty("metadata") private String metadata;
 * @JsonProperty("brand") private BrandDto brand;
 * @JsonProperty("store_type") private String storeType;
 * @JsonProperty("created_at") private String createdAt;
 * @JsonProperty("updated_at") private String updatedAt;
 */

public class Validator {
    private static String STORE_NAME = "name";
    private static String STORE_CONTACT_NAME = "contactPersonName";
    private static String STORE_CONTACT_PHONE = "contactPhone";
    private static String STORE_CONTACT_EMAIL = "contactEmail";
    private static String STORE_ADDRESS_LINE1 = "addressLine1";
    private static String STORE_ADDRESS_CITY = "city";
    private static String STORE_ADDRESS_STATE = "state";
    private static String STORE_ADDRESS_COUNTRY = "country";
    private static String STORE_ADDRESS_ZIP = "zipCode";

    public static void isStoreIdValid(Long storeId) {
        if (storeId == null || storeId <= 0) {
            throw new IllegalArgumentException("Store ID is required");
        }
    }

    public static void isSaveStoreValid(Map<String, String> storeData) {

        if (StringUtils.isBlank(storeData.get(STORE_NAME))){
            throw new IllegalArgumentException("Store name is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_CONTACT_NAME))) {
            throw new IllegalArgumentException("Store contact person name is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_CONTACT_PHONE))) {
            throw new IllegalArgumentException("Store contact phone is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_CONTACT_EMAIL))) {
            throw new IllegalArgumentException("Store contact email is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_ADDRESS_LINE1))) {
            throw new IllegalArgumentException("Store address line1 is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_ADDRESS_CITY))) {
            throw new IllegalArgumentException("Store address city is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_ADDRESS_STATE))) {
            throw new IllegalArgumentException("Store address state is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_ADDRESS_COUNTRY))) {
            throw new IllegalArgumentException("Store address country is required");
        }
        if (StringUtils.isBlank(storeData.get(STORE_ADDRESS_ZIP))) {
            throw new IllegalArgumentException("Store address zip code is required");
        }
    }
}
