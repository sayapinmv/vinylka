package ru.sayap.vinylka.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<List<OrderEntity>> findAllByUserId(UserEntity userEntity);
    Optional<OrderEntity> findById(Long id);

}
