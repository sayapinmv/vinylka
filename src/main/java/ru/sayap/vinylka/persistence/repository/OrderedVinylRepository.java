package ru.sayap.vinylka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.model.OrderEntity;
import ru.sayap.vinylka.persistence.model.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;

import java.util.UUID;

public interface OrderedVinylRepository extends JpaRepository<OrderedVinylEntity, UUID> {

    public OrderedVinylEntity getByOrderId(OrderEntity order);
}
