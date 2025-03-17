package ru.sayap.vinylka.persistence.order;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PROTECTED)
    Long id;

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
    @ToString.Exclude
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

    public enum PickPoints{

        MYTISHI("Sylikatnaya Street");

        private String pickPoint;

        PickPoints(String pickPoint){
            this.pickPoint = pickPoint;
        }

        public String getPickPoint(){
            return pickPoint;
        }

    }
}
