package com.trackservice.service.store;

import com.trackservice.dto.address.AddressDto;
import com.trackservice.dto.store.StoreDto;
import com.trackservice.entity.address.Address;
import com.trackservice.entity.product.Catalog;
import com.trackservice.entity.store.Store;
import com.trackservice.repository.catalog.CatalogRepository;
import com.trackservice.repository.store.StoreRepository;
import com.trackservice.util.CommonUtils;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final CatalogRepository catalogueRepository;

    public void addStore(StoreDto storeDto) throws Exception{
        try {
            Store store = convertDtoToStore(storeDto);
            storeRepository.save(store);
            catalogueRepository.save(createDefaultCatalogue(store));
        } catch (Exception e) {
            throw new Exception("Error while creating store");
        }
    }

    public List<StoreDto> getAllStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> stores = storeRepository.findAll(pageable);
        if (ObjectUtils.isEmpty(stores)) {
            throw new RuntimeException("No stores found");
        }
        return convertStoreToDto(stores.getContent());
    }

    public StoreDto getStoreById(long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        return convertStoreToDto(store);
    }

    private List<StoreDto> convertStoreToDto(List<Store> stores) {
        return stores.stream().map(this::convertStoreToDto).collect(Collectors.toList());
    }

    private Catalog createDefaultCatalogue(Store store) {
        Catalog catalog = Catalog.builder()
                .name("Default Catalogue")
                .description("Default Catalogue")
                .active(true)
                .createdAt(new Date())
                .updatedAt(new Date())
                .store(store)
                .build();
        return catalog;
    }

    private Address convertDtoToAddress(StoreDto storeDto) {
        Address address = Address.builder()
                .id(storeDto.getAddress().getId())
                .addressLine1(storeDto.getAddress().getAddressLine1())
                .addressLine2(storeDto.getAddress().getAddressLine2())
                .street(storeDto.getAddress().getStreet())
                .city(storeDto.getAddress().getCity())
                .state(storeDto.getAddress().getState())
                .country(storeDto.getAddress().getCountry())
                .zipCode(storeDto.getAddress().getZipCode())
                .build();
        return address;
    }

    private Store convertDtoToStore(StoreDto storeDto) {
        Date createdAt = new Date();
        if (StringUtils.isNotBlank(storeDto.getCreatedAt())){

        }
        Date updatedAt = new Date();
        if (StringUtils.isNotBlank(storeDto.getUpdatedAt())){

        }
        Store store = Store.builder()
                .id(storeDto.getId())
                .name(storeDto.getName())
                .description(storeDto.getDescription())
                .storeType(storeDto.getStoreType())
                .active(storeDto.isActive())
                .status(storeDto.getStatus())
                .registrationType(storeDto.getRegistrationType())
                .registrationNumber(storeDto.getRegistrationNumber())
                .contactPersonName(storeDto.getContactPersonName())
                .contactEmail(storeDto.getContactEmail())
                .contactPhone(storeDto.getContactPhone())
                .address(convertDtoToAddress(storeDto))
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
        return store;
    }

    private StoreDto convertStoreToDto(Store store) {

        StoreDto storeDto = StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .description(store.getDescription())
                .storeType(store.getStoreType())
                .active(store.isActive())
                .status(store.getStatus())
                .registrationType(store.getRegistrationType())
                .registrationNumber(store.getRegistrationNumber())
                .contactPersonName(store.getContactPersonName())
                .contactEmail(store.getContactEmail())
                .contactPhone(store.getContactPhone())
                .address(convertAddressToDto(store))
                .createdAt(CommonUtils.convertDateToString(store.getCreatedAt()))
                .updatedAt(CommonUtils.convertDateToString(store.getUpdatedAt()))
                .build();
        return storeDto;
    }

    private AddressDto convertAddressToDto(Store store) {
        Address address = store.getAddress();
        AddressDto addressDto = AddressDto.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
        return addressDto;
    }


}
