package ru.sayap.vinylka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.model.CartItemsEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;

import java.util.UUID;

public interface CartItemsRepository extends JpaRepository<CartItemsEntity, UUID> {
      CartItemsEntity deleteByCartIdAndVinylId(CartEntity cartEntity, VinylEntity vinylId);
//    CartItemsEntity findByCartAndVinyl(UUID cartId, VinylEntity vinylEntity);
}
