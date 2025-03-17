package ru.sayap.vinylka.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.persistence.user.UserRepository;
import ru.sayap.vinylka.persistence.vinyl.VinylEntity;
import ru.sayap.vinylka.rest.user.dto.AddUserRequest;
import ru.sayap.vinylka.service.user.mapper.UserServiceMapper;
import ru.sayap.vinylka.service.user.vo.UserVo;
import ru.sayap.vinylka.service.vinyl.mapper.VinylServiceMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    UserServiceMapper userServiceMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserServiceMapper userServiceMapper) {
        this.userRepository = userRepository;
        this.userServiceMapper = userServiceMapper;
    }

    @Override
    public List<UserVo> findAll(Integer page, Integer size) {

        return userServiceMapper.toUsersVo(userRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent());

    }

    @Override
    public UserVo findById(UUID id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));

        return new UserVo(
              id,
                userEntity.getFullName(),
                userEntity.getRoles(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserSex(),
                userEntity.getStatus()
        );

    }

//    public void addUser(UUID id, AddUserRequest addUserRequest ) {
//
//        UserEntity userEntity = new UserEntity(
//                id,
//                addUserRequest.fullName(),
//                addUserRequest.role(),
//                addUserRequest.phoneNumber(),
//                addUserRequest.email(),
//                addUserRequest.password(),
//                addUserRequest.sex(),
//                addUserRequest.status()
//        );
//
//
//
//    }

}
