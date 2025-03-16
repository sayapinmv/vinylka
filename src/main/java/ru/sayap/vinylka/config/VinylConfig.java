package ru.sayap.vinylka.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sayap.vinylka.persistence.cart.CartEntity;
import ru.sayap.vinylka.persistence.cart.CartRepository;
import ru.sayap.vinylka.persistence.cartitems.CartItemsEntity;
import ru.sayap.vinylka.persistence.cartitems.CartItemsRepository;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.user.UserRepository;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class VinylConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VinylRepository vinylRepository;

//    @Autowired
//    OrderRepository orderRepository;

//    @Autowired
//    CartItemsRepository cartItemsRepository;

    @Autowired
    CartRepository cartRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            var userList = List.of(
                    new UserEntity(
                            null,  // Or generate the UUID if needed
                            "The Greatest MaTBoT",
                            UserEntity.UserRole.USER,
                            "+7-921-333-12-13",
                            "sayapRules@dirty.ru",
                            "aaaaa",
                            UserEntity.UserSex.MAN,//Sex.MAN,  // userSex - add appropriate Sex enum value
                            UserEntity.UserStatus.ONLINE,//UserStatus.ONLINE,  // status - set a default status
                            null,
                            null
                    )
            );


            var vinylList = List.of(
                    new VinylEntity(
                            null,
                            "The Beatles",
                            "Please Please Me",
                            new BigDecimal("5000.0"),
                            LocalDate.of(1963, 12, 30),
                            LocalDate.of(1963, 12, 30),
                            "MaTBoT",
                            "RU",
                            12,
                            "First Beatles Album",
                            null
                    )
            );

//            var orderList = List.of(
//                new OrderEntity(
//                    null,
//                    userList.get(0),
//                    null,
//                    null,
//                    LocalDate.of(1963, 12, 30),
//                    new ArrayList<>()
//                )
//            );

            userList.forEach(userRepository::save);
            vinylList.forEach(vinylRepository::save);

            var cartList = List.of(
                new CartEntity(
                        null,
                        userList.get(0),
                        null
                )
            );

//            var cartItemsList = List.of(
//                    new CartItemsEntity(
//                        null,
//                        cartList.get(0),
//                        vinylList.get(0),
//                        12
//                    )
//            );
//
//            cartItemsRepository.saveAll(cartItemsList);

//            cartList.get(0).setItems(cartItemsList);

            cartList.forEach(cartRepository::save);


            userList.get(0).setCartEntity(cartList.get(0));
            userRepository.saveAll(userList);
            cartRepository.saveAll(cartList);

            vinylRepository.saveAll(vinylList);
//            orderRepository.saveAll(orderList);


//            var orderedVinylList = List.of(
//                    new OrderedVinylEntity() {
//
//                    }
//            )


        };
    }

}
