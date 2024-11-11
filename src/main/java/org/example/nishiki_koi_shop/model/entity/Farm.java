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
    private Long farmId;
    private String name;
    private String location;
    @Column(length = 3000)
    private String description;
    private String contactInfo;
    private String image;

    @Column(updatable = false)
    private LocalDate createdDate;
    private String ownerName;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
    }

    @OneToMany(mappedBy = "farm")
    private List<Fish> fishList;

    @OneToMany(mappedBy = "farm")
    private List<Tour> tourList;

    // New methods for required functionality
    public long getId() {
        return farmId;
    }

    public String getOwnerName() {
        return name;  // Assuming owner name is represented by 'name'
    }

    public int getSize() {
        return tourList.size();  // Assuming size is based on the number of tours
    }
}
