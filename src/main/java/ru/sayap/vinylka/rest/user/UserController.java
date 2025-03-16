package ru.sayap.vinylka.rest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.rest.user.dto.GetUserResponse;
import ru.sayap.vinylka.rest.user.mapper.UserControllerMapper;
import ru.sayap.vinylka.service.user.UserService;
import ru.sayap.vinylka.service.user.vo.UserVo;
//import ru.sayap.vinylka.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserService userService;
    private UserControllerMapper userControllerMapper;

    @Autowired
    public UserController(UserService userService, UserControllerMapper userControllerMapper) {
        this.userService = userService;
        this.userControllerMapper =userControllerMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        List<UserVo> allUserVo = userService.findAll();

        return ResponseEntity
                .ok()
                .body(userControllerMapper.toGetUserResponse(allUserVo));
    }
//
//    @GetMapping("/{id}")
//    public UserEntity getUserById(@PathVariable Long id) {
//
//
//
//    }
}
