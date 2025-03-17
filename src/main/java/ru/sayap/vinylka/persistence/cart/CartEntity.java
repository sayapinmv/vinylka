package ru.sayap.vinylka.persistence.cart;


import jakarta.persistence.*;
import lombok.*;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

// Является ли этот подход хорошим тоном или обязательным критерием @JdbcTypeCode(SqlTypes.VARCHAR)

public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CartItemsEntity> items;

}
