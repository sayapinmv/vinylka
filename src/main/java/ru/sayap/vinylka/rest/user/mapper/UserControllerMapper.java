package ru.sayap.vinylka.rest.user.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.rest.user.dto.GetUserResponse;
import ru.sayap.vinylka.service.user.vo.UserVo;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserControllerMapper {

    GetUserResponse toGetUserResponse(UserVo userVo);
    List<GetUserResponse> toGetUserResponse(List<UserVo> userVo);

}
