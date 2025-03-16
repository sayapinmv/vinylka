package ru.sayap.vinylka.persistence.vinyl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VinylRepository extends JpaRepository<VinylEntity, Long> {

    //public void deleteById (Long id);

}
