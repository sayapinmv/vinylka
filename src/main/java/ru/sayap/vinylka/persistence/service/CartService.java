package ru.sayap.vinylka.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.model.CartEntity;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.persistence.repository.CartItemsRepository;
import ru.sayap.vinylka.persistence.repository.CartRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CartService {

    CartRepository cartRepository;
    CartItemsRepository cartItemsRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemsRepository cartItemsRepository) {
        this.cartRepository = cartRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    public CartEntity getCart(UserEntity userEntity) {
         return cartRepository.getByUserId(userEntity);
    }

    public void addItemToCart(UserEntity user, VinylEntity vinylEntity, int quantity) {

        CartEntity cartEntity = getCart(user);

        if (cartEntity == null) {
            cartEntity = new CartEntity();
            cartEntity.setUserId(user);
            cartEntity = cartRepository.save(cartEntity);
        }

        CartItemsEntity cartItemsEntity = cartItemsRepository.findByCartAndVinyl(cartEntity, vinylEntity);

        if (cartItemsEntity != null) {
            cartItemsEntity.setQnty(cartItemsEntity.getQnty() + quantity);
        } else {
            cartItemsEntity = new CartItemsEntity();
            cartItemsEntity.setVinylId(vinylEntity);
            cartItemsEntity.setCartId(cartEntity);
            cartItemsEntity.setQnty(quantity);
        }


        cartItemsRepository.save(cartItemsEntity);
    }

    public void removeItemFromCart(UserEntity user, VinylEntity vinyl) {
        CartEntity cart = getCart(user);
        if (cart != null) {
            cartItemsRepository.deleteByCartIdAndVinylId(cart, vinyl);
        }
        else{
            throw new RuntimeException("Cart item not found");
        }
    }

    public Set<CartItemsEntity> viewCartItems(UserEntity userEntity) {
        CartEntity cartEntity = getCart(userEntity);
        return (cartEntity != null) ? cartEntity.getItems() : null;
    }



}
