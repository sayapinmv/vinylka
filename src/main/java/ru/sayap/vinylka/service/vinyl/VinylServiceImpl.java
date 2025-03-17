package ru.sayap.vinylka.service.vinyl;

import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.service.vinyl.mapper.VinylServiceMapper;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
public class VinylServiceImpl implements VinylService {

    private final VinylRepository vinylRepository;
    private final VinylServiceMapper vinylServiceMapper;

    @Autowired
    public VinylServiceImpl(VinylRepository vinylRepository, VinylServiceMapper vinylServiceMapper) {
        this.vinylRepository = vinylRepository;
        this.vinylServiceMapper = vinylServiceMapper;
    }

    @Override
    public VinylVo getVinyl(Long id) {

        VinylEntity vinylEntity = vinylRepository
                .findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new NoSuchElementException("Vinyl by specified id not found"));

        return vinylServiceMapper.toVinylVo(vinylEntity);

    }


    @Override
    public List<VinylVo> getVinylList() {
        return vinylRepository.findAll()
                .stream()
                .map(vinylServiceMapper::toVinylVo)
                .toList();
    }

    @Override
    @Transactional
    public VinylVo create(CreateVinylRequest createVinylRequest) {


        VinylEntity newVinylEntity = new VinylEntity();

        newVinylEntity.setAlbum(createVinylRequest.album());
        newVinylEntity.setAuthor(createVinylRequest.author());
        newVinylEntity.setPrice(createVinylRequest.price());
        newVinylEntity.setEditionYear(createVinylRequest.editionYear());
        newVinylEntity.setPublicationYear(createVinylRequest.publicationYear());
        newVinylEntity.setLabel(createVinylRequest.label());
        newVinylEntity.setPublicationCountry(createVinylRequest.publicationCountry());
        newVinylEntity.setQnty(createVinylRequest.qnty());
        newVinylEntity.setDescription(createVinylRequest.description());

        vinylRepository.save(newVinylEntity);

        return vinylServiceMapper.toVinylVo(newVinylEntity);

    }

    @Override
    @Transactional
    public VinylVo update(Long id, UpdateVinylRequest updateVinylRequest) {

       VinylEntity vinylEntity = vinylRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such filed by specified id"));

       vinylEntity.setAlbum(updateVinylRequest.album());
       vinylEntity.setAuthor(updateVinylRequest.author());
       vinylEntity.setPrice(updateVinylRequest.price());
       vinylEntity.setEditionYear(updateVinylRequest.editionYear());
       vinylEntity.setPublicationYear(updateVinylRequest.publicationYear());
       vinylEntity.setLabel(updateVinylRequest.label());
       vinylEntity.setPublicationCountry(updateVinylRequest.publicationCountry());
       vinylEntity.setQnty(updateVinylRequest.qnty());
       vinylEntity.setDescription(updateVinylRequest.description());

       VinylEntity updatedVinylEntity = vinylRepository.save(vinylEntity);

       return vinylServiceMapper.toVinylVo(updatedVinylEntity);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        VinylEntity vinylEntity =  vinylRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such filed by specified id"));

        vinylRepository.delete(vinylEntity);

    }

}
