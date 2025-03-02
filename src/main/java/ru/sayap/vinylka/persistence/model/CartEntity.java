package ru.sayap.vinylka.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@Data
public class CartEntity {

    @Id
    @GeneratedValue
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userId;

    @OneToMany(mappedBy = "cartId")
    private Set<CartItemsEntity> items;

}
