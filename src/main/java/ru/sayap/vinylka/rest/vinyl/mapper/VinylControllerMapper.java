package ru.sayap.vinylka.rest.vinyl.mapper;


import org.mapstruct.Mapper;
import ru.sayap.vinylka.rest.vinyl.dto.CreateVinylRequest;
import ru.sayap.vinylka.rest.vinyl.dto.GetVinylDto;
import ru.sayap.vinylka.rest.vinyl.dto.UpdateVinylRequest;
import ru.sayap.vinylka.service.vinyl.vo.VinylVo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VinylControllerMapper {

    //@Mapping(target = "id", source = "id")
    List<GetVinylDto> toGetVinylDto(List<VinylVo> getVinylVo);

    //@Mapping(target = "id", source = "id")
    GetVinylDto toGetVinylDto(VinylVo getVinylVo);

}
