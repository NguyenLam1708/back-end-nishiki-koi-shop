package org.example.nishiki_koi_shop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tourId;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private long price;
    private Integer maxParticipants;
    private String image;

    @Column(updatable = false)
    private LocalDate createdDate;

    @PrePersist
    private void onCreate() {
        this.createdDate = LocalDate.now();
    }

    //khi xoa 1 farm bat ki thi se khong con 1 tour nao cua farm do xuat hien nua
    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Farm farm;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderTourDetail> orderTourDetail;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;
}
