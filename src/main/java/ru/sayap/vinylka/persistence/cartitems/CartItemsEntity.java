package ru.sayap.vinylka.persistence.cartitems;

import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CartItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    CartEntity cartId;

    @ManyToOne
    @JoinColumn(name = "vinyl_id")
    VinylEntity vinylId;

    @Column(name = "quantity")
    Integer qnty;

}
