package com.example.nishikiKoiShop.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    private Double price;
    private Integer capacity;
    private String schedule;
}
