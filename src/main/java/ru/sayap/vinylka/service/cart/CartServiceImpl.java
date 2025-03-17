package ru.sayap.vinylka.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.cart.CartRepository;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.persistence.cartitems.CartItemsRepository;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.user.UserRepository;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;
import ru.sayap.vinylka.rest.cart.dto.AddItemRequest;
import ru.sayap.vinylka.service.cart.mapper.CartServiceMapper;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Правда не понимаю, зачем может понадобиться два сервиса для карт и их сожержимого, возможно стоит часть функционала
// делегировать. Хотя один ваш коллега по цеху говорил мне, что это дает более гибкий доступ к данным.
// Правда в его словах наверное есть и если вы, магистр, настоите на том, что это необходимо, я обязательно это сделаю.
// До тех пор получается так, что мне нужен маппер и во от содержимого карты, а все остальные манипуляции производятся
// непосредственно через карту

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartServiceMapper cartServiceMapper;
    private VinylRepository vinylRepository;
    private UserRepository userRepository;
    private CartItemsRepository cartItemsRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartServiceMapper cartServiceMapper, VinylRepository vinylRepository, UserRepository userRepository, CartItemsRepository cartItemsRepository) {
        this.cartRepository = cartRepository;
        this.cartServiceMapper = cartServiceMapper;
        this.vinylRepository = vinylRepository;
        this.userRepository = userRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    @Override
    public List<CartItemsVo> getCartItemsByCartId(UUID userId, Integer page, Integer size) {

        //    List<CartItemsEntity> cartItemsEntities = cartRepository.findByCartId(cartId);
        //        return cartItemsEntities.stream()
        //                .map(this::mapToCartItemsVo)
        //                .collect(Collectors.toList());

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        CartEntity cartEntity = cartRepository
                .findByUserId(userEntity)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        return cartServiceMapper.toCartItemsVoList(
                cartItemsRepository.findByCartId(cartEntity, Pageable.ofSize(size).withPage(page)).getContent());
    }

    @Override
    public CartItemsVo addCartItem(AddItemRequest addItemRequest, UUID userId) {

        // Тут необходимо понять, как лучше стоит проверять наличие товара в корзине
        // т.е. я добавляю товар в корзину и если он там уже есть, то просто увеличиваю кол-во
        // на то кол-во, которое мне поступило в запросе. Мне, почему то, кажеся, что подобные ситуации
        // могут решаться как то более изощреннее, чем простая итерация по массиву циклом фор

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        CartEntity cartEntity = cartRepository.findByUserId(userEntity).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        VinylEntity vinylEntity = vinylRepository.findById(addItemRequest.vinylId()).orElseThrow(() -> new IllegalArgumentException("Invalid vinyl id"));

        CartItemsEntity cartItemsEntity = new CartItemsEntity();

        cartItemsEntity.setCartId(cartEntity);
        cartItemsEntity.setVinylId(vinylEntity);
        cartItemsEntity.setQnty(addItemRequest.quantity());


        if (cartEntity.getItems().isEmpty()) {

            List<CartItemsEntity> newItems = new ArrayList<>();
            cartEntity.setItems(newItems);

        }

        cartEntity.getItems().add(cartItemsEntity);
        cartRepository.save(cartEntity);

        return cartServiceMapper.toCartItemsVo(cartItemsEntity);

    }

    // Я не понимаю почему не удаляется товар из карты

    @Override
    public void removeCartItem(Long vinylId, UUID userId) {

        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));

        CartEntity cartEntity = cartRepository
                .findByUserId(userEntity)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));


        boolean isRemoved = cartEntity.getItems().removeIf(cartItem -> cartItem.getVinylId().getId().equals(vinylId));


        if (!isRemoved) {

            throw new IllegalArgumentException("Item not found");

        }

        cartRepository.save(cartEntity);

    }

}

