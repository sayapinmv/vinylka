package ru.sayap.vinylka.persistence.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByUserId(UserEntity userEntity, Pageable pageable);
    Optional<OrderEntity> findById(Long id);

}
