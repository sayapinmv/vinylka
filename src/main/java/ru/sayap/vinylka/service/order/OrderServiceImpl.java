package ru.sayap.vinylka.service.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.order.OrderEntity;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.order.OrderRepository;
import ru.sayap.vinylka.persistence.orderedvinyl.OrderedVinylRepository;
import ru.sayap.vinylka.persistence.user.UserRepository;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;
import ru.sayap.vinylka.rest.order.dto.CreateNewOrderRequest;
import ru.sayap.vinylka.service.order.mapper.OrderServiceMapper;
import ru.sayap.vinylka.service.order.vo.OrderVo;
import ru.sayap.vinylka.service.vinyl.VinylService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderServiceMapper orderServiceMapper;
    private  UserRepository userRepository;
    private OrderRepository orderRepository;
    private VinylRepository vinylRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderServiceMapper orderServiceMapper, VinylRepository vinylRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderServiceMapper = orderServiceMapper;
        this.vinylRepository = vinylRepository;
    }

    @Override
    public List<OrderVo> getOrders(UUID userId, Integer page, Integer size) {

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println(userEntity.getUserSex());

        List<OrderEntity> orderList = orderRepository
                .findAllByUserId(userEntity, Pageable.ofSize(size).withPage(page)).getContent();

        System.out.println(orderList.size());

        List<OrderVo> order = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            OrderVo orderVo = orderServiceMapper.toOrderVo(orderList.get(i));

            orderVo.setIsPaid(true);
            orderVo.setTotalPrice(calculatePrice(orderList.get(i).getOrderedVinylEntity()));
            order.add(orderVo);
        }

        return order;

    }

    @Override
    public OrderVo getOrder(UUID userId, Long orderId) {


        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (userId != order.getUserId().getId()) {
            throw new RuntimeException("User id mismatch");
        }

        BigDecimal totalPrice = calculatePrice(order.getOrderedVinylEntity());

        return new OrderVo(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getDeliveryType(),
                order.getOrderDate(),
                // пока что по дефолту оплата прошла
                true,
                totalPrice,
                order.getOrderedVinylEntity()
        );
    }

    private BigDecimal calculatePrice(List<OrderedVinylEntity> orderedVinyl) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (int i = 0; i < orderedVinyl.size(); i++) {

            totalPrice = totalPrice.add(orderedVinyl.get(i).getPrice());

        }

        return totalPrice;

    }

    @Override
    public void createOrder(CreateNewOrderRequest newOrder, UUID userId) {

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        OrderEntity newOrderEntity = new OrderEntity();

        newOrderEntity.setUserId(userEntity);

        if (Arrays.stream(OrderEntity.PickPoints.values()).anyMatch(pickPoint -> pickPoint.getPickPoint().equals(newOrder.address()))) {
            newOrderEntity.setDeliveryType(OrderEntity.Delivery.PICKUP);
        } else {
            newOrderEntity.setDeliveryType(OrderEntity.Delivery.SERVICE);
        }

        newOrderEntity.setStatus(OrderEntity.OrderStatus.CREATED);
        newOrderEntity.setOrderDate(LocalDate.now());

        List<OrderedVinylEntity> newOrderedVinylEntity = new ArrayList<>();

        for (int i = 0; i < newOrder.vinylIdList().size(); i++) {

            VinylEntity vinyl = vinylRepository.findById(newOrder.vinylIdList().get(i)).orElseThrow(() -> new RuntimeException("Vinyl not found"));

            OrderedVinylEntity orderedVinylEntity = new OrderedVinylEntity();

            orderedVinylEntity.setAlbumName(vinyl.getAlbum());
            orderedVinylEntity.setAuthorName(vinyl.getAuthor());
            orderedVinylEntity.setPrice(vinyl.getPrice());
            orderedVinylEntity.setEditionYear(vinyl.getEditionYear());
            orderedVinylEntity.setPublicationYear(vinyl.getPublicationYear());
            orderedVinylEntity.setLabel(vinyl.getLabel());
            orderedVinylEntity.setPublicationCountry(vinyl.getPublicationCountry());
            orderedVinylEntity.setDescription(vinyl.getDescription());
            orderedVinylEntity.setQuantity(vinyl.getQnty());


            newOrderedVinylEntity.add(orderedVinylEntity);

            newOrderedVinylEntity.get(i).getVinylId().add(vinyl);

        }

        newOrderEntity.setOrderedVinylEntity(newOrderedVinylEntity);

        orderRepository.save(newOrderEntity);

    }

    @Override
    public void cancelOrder(Long orderId, UUID userId) {

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        System.out.println("user in order: "+order.getUserId().getId()+" order request "+userId);


//        if (order.getUserId().getId() != userId) {
//            throw new RuntimeException("User id mismatch");
//        }

        order.setStatus(OrderEntity.OrderStatus.CANCELED);

        orderRepository.save(order);

    }

}
