package ru.sayap.vinylka.persistence.cart;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@AllArgsConstructor
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
