package ru.sayap.vinylka.service.order.vo;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderVo {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "status")
    OrderEntity.OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    OrderEntity.Delivery deliveryType;

    @Column(name = "order_date")
    LocalDate orderDate;

    @OneToMany(mappedBy = "orderId")
    @ToString.Exclude
    List<OrderedVinylEntity> orderedVinylEntity;
}
