package ru.sayap.vinylka.service.cart;

import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;

import java.util.Set;

public interface CartService {
    CartEntity getCart(UserEntity userEntity);

    void removeItemFromCart(UserEntity user, VinylEntity vinyl);

    Set<CartItemsEntity> viewCartItems(UserEntity userEntity);
}
