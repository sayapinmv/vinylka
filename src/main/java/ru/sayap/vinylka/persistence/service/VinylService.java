package ru.sayap.vinylka.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.persistence.repository.VinylRepository;

import java.util.List;
import java.util.UUID;


@Service
public class VinylService {

    private VinylRepository vinylRepository;

    @Autowired
    public VinylService(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    public void save(VinylEntity vinylEntity) {
        vinylRepository.save(vinylEntity);
    }

    public List<VinylEntity> findAll() {
        return vinylRepository.findAll();
    }

    public VinylEntity findById(UUID id) {
        return vinylRepository.findById(id).orElse(null);
    }

}
