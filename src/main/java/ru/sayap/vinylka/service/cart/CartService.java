package ru.sayap.vinylka.service.cart;

import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.rest.cart.dto.AddItemRequest;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;
import java.util.List;
import java.util.UUID;

public interface CartService {

    List<CartItemsVo> getCartItemsByCartId(UUID userId, Integer page, Integer size);
    CartItemsVo addCartItem(AddItemRequest addItemRequest, UUID userId);
    void removeCartItem(Long vinylId, UUID userId);

}
