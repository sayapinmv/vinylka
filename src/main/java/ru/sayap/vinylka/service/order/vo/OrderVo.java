package ru.sayap.vinylka.service.order.vo;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderVo {

    private Long id;

    private UserEntity userId;

    private OrderEntity.OrderStatus status;

    private OrderEntity.Delivery deliveryType;

    private LocalDate orderDate;

    private Boolean isPaid;

    private BigDecimal totalPrice;

    private List<OrderedVinylEntity> orderedVinylEntity;
}
