package ru.sayap.vinylka.persistence.cartitems;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;

import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItemsEntity, Long> {
}
