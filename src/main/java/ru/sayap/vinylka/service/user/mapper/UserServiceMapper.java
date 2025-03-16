package ru.sayap.vinylka.service.user.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.service.user.vo.UserVo;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserServiceMapper {

    UserVo toUserVo(UserEntity userEntity);
    List<UserVo> toUsersVo(List<UserEntity> userEntity);

}
