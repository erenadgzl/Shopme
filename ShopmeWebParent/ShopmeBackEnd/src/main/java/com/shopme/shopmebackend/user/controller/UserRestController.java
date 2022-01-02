package com.shopme.shopmebackend.user.controller;

import com.shopme.shopmebackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/check-email")
    public String checkDublicateEmail(@Param("email") String email, @Param("id") Integer id) {
        return userService.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
