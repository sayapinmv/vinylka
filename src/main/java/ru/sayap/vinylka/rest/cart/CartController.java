package ru.sayap.vinylka.rest.cart;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.persistence.user.UserEntity;
//import ru.sayap.vinylka.rest.cart.dto.GetCartResponse;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.rest.cart.dto.AddItemRequest;
import ru.sayap.vinylka.rest.cart.dto.DeleteItemRequest;
import ru.sayap.vinylka.rest.cart.dto.GetCartRequest;
import ru.sayap.vinylka.rest.cart.mapper.CartControllerMapper;
import ru.sayap.vinylka.service.cart.CartService;
import ru.sayap.vinylka.service.cart.vo.CartItemsVo;
import ru.sayap.vinylka.service.user.UserService;
import ru.sayap.vinylka.service.vinyl.VinylService;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;
//import ru.sayap.vinylka.service.UserService;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
//import ru.sayap.vinylka.rest.vinyl.model.Vinyl;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final VinylService vinylService;
    private final UserService userService;
    CartService cartService;
    CartControllerMapper cartControllerMapper;

    @Autowired
    public CartController(CartService cartService, CartControllerMapper cartControllerMapper, VinylService vinylService, UserService userService) {
        this.cartService = cartService;
        this.cartControllerMapper = cartControllerMapper;
        this.vinylService = vinylService;
        this.userService = userService;
    }

    // передача id по ссылке временное решение, разумеется

    @GetMapping()
    public ResponseEntity<List<GetCartRequest>> getCartItems(@RequestParam("id") Optional<UUID> userId) {

        if (userId.isPresent()) {
            List<CartItemsVo> cartItemsVo = cartService.getCartItemsByCartId(userId.get());

            return ResponseEntity.ok().body(cartControllerMapper.toGetCartRequest(cartItemsVo));
        }

        throw new IllegalArgumentException("userId is not present");

    }

    @PostMapping
    public ResponseEntity<GetCartRequest> addItemToCart(@RequestParam("id") Optional<UUID> userId, @RequestBody @NotNull AddItemRequest addItemRequest) {

        if (userId.isPresent()) {

            CartItemsVo cartItem = cartService.addCartItem(addItemRequest, userId.get());

            return ResponseEntity.ok().body(cartControllerMapper.toGetCartRequest(cartItem));

        }

        throw new IllegalArgumentException("userId is not present");
    }


    @DeleteMapping
    public ResponseEntity<String> removeFromCart(@RequestParam("id") Optional<UUID> userId, @RequestBody @NotNull DeleteItemRequest itemForDelete) {

        if (userId.isPresent()) {
            cartService.removeCartItem(itemForDelete.vinylId(), userId.get());

            return ResponseEntity.ok().body("Vinyl successfully removed from cart");
        }

        throw new IllegalArgumentException("userId is not present");

    }

}

