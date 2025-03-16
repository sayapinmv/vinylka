package ru.sayap.vinylka.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.order.OrderRepository;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylRepository;
import ru.sayap.vinylka.persistence.user.UserRepository;
import ru.sayap.vinylka.service.order.mapper.OrderServiceMapper;
import ru.sayap.vinylka.service.order.vo.OrderVo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderServiceMapper orderServiceMapper;
    private  UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderServiceMapper orderServiceMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderServiceMapper = orderServiceMapper;
    }

    @Override
    public List<OrderVo> getOrders(UUID userId) {

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderEntity> orderList = orderRepository
                .findAllByUserId(userEntity)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderServiceMapper.toOrderVo(orderList);

    }

    @Override
    public OrderVo getOrder(UUID userId, Long orderId) {


        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (userId != order.getUserId().getId()) {
            throw new RuntimeException("User id mismatch");
        }

        return orderServiceMapper.toOrderVo(order);
    }

    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public void cancelOrder(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderRepository.delete(orderEntity);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

}
