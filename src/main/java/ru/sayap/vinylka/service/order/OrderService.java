package ru.sayap.vinylka.service.order;

import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderVo> getOrders(UUID userId);

    OrderVo getOrder(UUID userId, Long orderId);

}
