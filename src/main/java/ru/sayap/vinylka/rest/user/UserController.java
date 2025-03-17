package ru.sayap.vinylka.rest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sayap.vinylka.persistence.user.UserEntity;
import ru.sayap.vinylka.rest.user.dto.GetUserResponse;
import ru.sayap.vinylka.rest.user.mapper.UserControllerMapper;
import ru.sayap.vinylka.service.user.UserService;
import ru.sayap.vinylka.service.user.vo.UserVo;
//import ru.sayap.vinylka.service.UserService;

import javax.swing.*;
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
    public ResponseEntity<List<GetUserResponse>> getAllUsers(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "size", defaultValue = "50") Integer size) {
        List<UserVo> allUserVo = userService.findAll(page, size);

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
