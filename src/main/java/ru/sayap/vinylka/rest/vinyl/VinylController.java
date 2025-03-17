package ru.sayap.vinylka.rest.vinyl;


import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.vinyl.VinylRepository;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.GetVinylDto;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.mapper.VinylControllerMapper;
import ru.sayap.vinylka.service.vinyl.VinylService;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vinyl")
public class VinylController {

    private final VinylService vinylService;
    private final VinylControllerMapper vinylControllerMapper;

    @Autowired
    public VinylController(VinylService vinylService, VinylControllerMapper vinylControllerMapper) {
        this.vinylService = vinylService;
        this.vinylControllerMapper = vinylControllerMapper;
    }


    // Мне кажется Integer можно заменить на простой int
    @GetMapping
    public ResponseEntity<List<GetVinylDto>> getAllVinyl(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "50") Integer size) {

        List<VinylVo> getVinylVo = vinylService.getVinylList(size, page);

        return ResponseEntity.ok(vinylControllerMapper.toGetVinylDto(getVinylVo));

        //throw new IllegalArgumentException("Required parameter (page or size) not provided, unfortunately");

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetVinylDto> getVinyl(@PathVariable("id") Long id) {

        VinylVo vinylVo = vinylService.getVinyl(id);

        return ResponseEntity.ok(vinylControllerMapper.toGetVinylDto(vinylVo));
    }


    @PutMapping
    public ResponseEntity<GetVinylDto> createVinyl(@RequestBody @NotNull CreateVinylRequest body) {

        VinylVo vinylVo = vinylService.create(body);

        return ResponseEntity.created(URI.create("/%s".formatted(vinylVo.getId())))
                .body(vinylControllerMapper.toGetVinylDto(vinylVo));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetVinylDto> updateVinyl(@PathVariable("id") Long id, @RequestBody @NotNull UpdateVinylRequest body) {

        VinylVo updatedVinylVo = vinylService.update(id, body);

        return ResponseEntity.created(URI.create("/%s".formatted(updatedVinylVo.getId())))
                .body(vinylControllerMapper.toGetVinylDto(updatedVinylVo));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVinyl(@PathVariable("id") @NotNull Long id) {

        vinylService.deleteById(id);
        return ResponseEntity.ok().body("vinyl deleted successfully");

    }

}