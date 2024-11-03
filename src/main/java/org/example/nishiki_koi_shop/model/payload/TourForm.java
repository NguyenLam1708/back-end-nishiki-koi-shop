package org.example.nishiki_koi_shop.model.payload;

import lombok.Data;

@Data
public class TourForm {
    private String name;
    private String description;
    private long price;

    // Default constructor
    public TourForm() {}

    // Getters for fields
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }
}
