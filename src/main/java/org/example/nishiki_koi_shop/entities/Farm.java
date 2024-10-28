package com.example.nishikiKoiShop.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private Integer quantity;
    private String description;
    private Integer age;
    private String weight;
    private String color;
}
