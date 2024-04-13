package com.trackservice.dto.catalog;

import java.util.List;

public class ModifierOptionDto {
    private long id;
    private String name;
    private double price;
    private boolean active;
    private List<ModifierGroupDto> modifierGroups;
}
