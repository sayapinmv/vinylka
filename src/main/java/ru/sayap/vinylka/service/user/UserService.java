package ru.sayap.vinylka.service.user;


import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.service.user.vo.UserVo;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserVo> findAll();

    public UserVo findById(UUID id);

}
