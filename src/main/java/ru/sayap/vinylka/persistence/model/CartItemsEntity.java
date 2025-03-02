package ru.sayap.vinylka.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@Data
public class CartItemsEntity {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    CartEntity cartId;

    @ManyToOne
    @JoinColumn(name = "vinyl_id")
    VinylEntity vinylId;

    @Column(name = "quantity")
    int qnty;

}
