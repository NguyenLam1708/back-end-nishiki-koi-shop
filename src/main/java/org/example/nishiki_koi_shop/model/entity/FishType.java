package org.example.nishiki_koi_shop.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fish_types")
public class FishType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fishTypeId;

    private String name;
    @Column(length = 3000)
    private String description;

    @OneToMany(mappedBy = "fishType")
    private List<Fish> fish;

    @Column(updatable = false)
    private LocalDate createdDate;

    @PrePersist
    private void OnCreate() {
        this.createdDate = LocalDate.now();
    }
}
