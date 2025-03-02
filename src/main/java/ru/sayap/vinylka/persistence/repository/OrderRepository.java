package ru.sayap.vinylka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.model.CartEntity;
import ru.sayap.vinylka.persistence.model.OrderEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    List<OrderEntity> findByUserId(UserEntity userEntity);

}
