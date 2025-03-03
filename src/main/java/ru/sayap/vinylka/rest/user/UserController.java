package ru.sayap.vinylka.rest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sayap.vinylka.persistence.model.UserEntity;
import ru.sayap.vinylka.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserEntity> user() {
        return userService.findAll();
    }
}
