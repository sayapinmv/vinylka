package ru.sayap.vinylka.persistence.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    public Optional<CartEntity> findCartByUserId(UserEntity userId);



}
