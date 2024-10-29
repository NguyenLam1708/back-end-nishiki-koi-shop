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
@Table(name = "farm")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long farmId;

    private String name;
    private String location;
    private String description;
    private LocalDate createdDate;
    private String contactInfo;

    @OneToMany(mappedBy = "farm")
    private List<Fish> fishList;

    @OneToMany(mappedBy = "farm")
    private List<Tour> tourList;

}
