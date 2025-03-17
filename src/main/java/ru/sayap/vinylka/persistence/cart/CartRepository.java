package ru.sayap.vinylka.persistence.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findByUserId(UserEntity userEntity);

}
