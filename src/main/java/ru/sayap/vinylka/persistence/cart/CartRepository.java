package ru.sayap.vinylka.persistence.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.model.UserEntity;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    CartEntity getByUserId(UserEntity user);
}
