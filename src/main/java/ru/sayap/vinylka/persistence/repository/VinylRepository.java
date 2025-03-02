package ru.sayap.vinylka.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sayap.vinylka.persistence.model.VinylEntity;

import java.util.UUID;

public interface VinylRepository extends JpaRepository<VinylEntity, UUID> {
}
