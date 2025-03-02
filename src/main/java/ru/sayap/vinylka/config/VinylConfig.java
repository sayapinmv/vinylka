package ru.sayap.vinylka.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sayap.vinylka.persistence.model.OrderEntity;
import ru.sayap.vinylka.persistence.model.OrderedVinylEntity;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.persistence.repository.OrderRepository;
import ru.sayap.vinylka.persistence.repository.OrderedVinylRepository;
import ru.sayap.vinylka.persistence.repository.UserRepository;
import ru.sayap.vinylka.persistence.repository.VinylRepository;

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

    @Autowired
    OrderRepository orderRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            var userList = List.of(
                    new UserEntity(
                            null,  // Or generate the UUID if needed
                            "The Greatest MaTBoT",
                            List.of("Admin", "Authorized"),
                            "+7-921-333-12-13",
                            "sayapRules@dirty.ru",
                            "aaaaa",
                            null,//Sex.MAN,  // userSex - add appropriate Sex enum value
                            null,//UserStatus.ONLINE,  // status - set a default status
                            new ArrayList<>(),  // Initialize orderEntities
                            new ArrayList<>(),  // Initialize cartEntities
                            new ArrayList<>()
                    )
            );

            userRepository.saveAll(userList);  // Ensure the user is saved to the repository

            var vinylList = List.of(
                    new VinylEntity (
                            null,
                            "The Beatles",
                            "Please Please Me",
                            new BigDecimal("5000.0"),
                            LocalDate.of(1963, 12, 30),
                            LocalDate.of(1963, 12, 30),
                            "MaTBoT",
                            "RU",
                            "First Beatles Album",
                            new ArrayList<>(),
                            new ArrayList<>()
                    )
            );

            var orderList = List.of(
                    new OrderEntity(
                            null,
                            userList.get(0),
                            null,
                            null,
                            LocalDate.of(1963, 12, 30),
                            new ArrayList<>()
                    )
            );

            vinylRepository.saveAll(vinylList);
            orderRepository.saveAll(orderList);

//            var orderedVinylList = List.of(
//                    new OrderedVinylEntity() {
//
//                    }
//            )


        };
    }

}
