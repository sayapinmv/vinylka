package ru.sayap.vinylka.persistence.vinyl;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VinylRepository extends JpaRepository<VinylEntity, Long> {

    Page<VinylEntity> findAll(Pageable pageable);
    //List<VinylEntity> findAll();

}
