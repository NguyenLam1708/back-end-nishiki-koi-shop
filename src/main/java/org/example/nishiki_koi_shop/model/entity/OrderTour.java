package org.example.nishiki_koi_shop.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_tour")
public class OrderTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderTourId;

    private long totalAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate  tourBookingDate;
    private LocalDate  tourStartDate;
    private String paymentMethod;
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "orderTour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderTourDetail orderTourDetail;

    public enum Status {
        PENDING, APPROVED, REJECTED, CANCELLED
    }
}
