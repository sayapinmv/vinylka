package ru.sayap.vinylka.service.vinyl;

import org.antlr.v4.runtime.misc.NotNull;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

import java.util.List;

public interface VinylService {

    public List<VinylVo> getVinylList(int size, int page);

    public VinylVo getVinyl(Long id);

    public VinylVo update(Long id, UpdateVinylRequest updateVinylRequest);

    public VinylVo create(CreateVinylRequest createVinylRequest);

    public void deleteById(Long id);

}
