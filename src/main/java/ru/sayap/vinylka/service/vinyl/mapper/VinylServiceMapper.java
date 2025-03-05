package ru.sayap.vinylka.service.vinyl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.sayap.vinylka.persistence.model.VinylEntity;
import ru.sayap.vinylka.service.vinyl.model.VinylModel;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VinylServiceMapper {
    VinylEntity toEntity(VinylModel vinylEntityDto);

    VinylModel toVinylEntityDto(VinylEntity vinylEntity);
}