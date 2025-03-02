package ru.sayap.vinylka.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEntity {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "status")
    OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    Delivery deliveryType;

    @Column(name = "order_date")
    LocalDate orderDate;

    @OneToMany(mappedBy = "orderId")
    List<OrderedVinylEntity> orderedVinylEntity;

    public enum  OrderStatus {
        CREATED,
        DELIVERED,
        PENDING,
        CANCELED,
        COMPLETED
    }

    public enum Delivery{
        PICKUP,
        SERVICE
    }
}
