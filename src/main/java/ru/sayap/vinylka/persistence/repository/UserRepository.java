package ru.sayap.vinylka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.model.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
