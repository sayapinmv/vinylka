package ru.sayap.vinylka.service.vinyl.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VinylServiceMapper {

    VinylVo toVinylVo(VinylEntity vinylEntity);

}
