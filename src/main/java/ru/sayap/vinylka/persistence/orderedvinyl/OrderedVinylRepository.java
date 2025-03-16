package ru.sayap.vinylka.persistence.orderedvinyl;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.order.OrderEntity;

public interface OrderedVinylRepository extends JpaRepository<OrderedVinylEntity, Long> {

    public OrderedVinylEntity getByOrderId(OrderEntity order);
}
