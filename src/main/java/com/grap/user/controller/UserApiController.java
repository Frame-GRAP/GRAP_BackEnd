package com.grap.user.controller;

import com.grap.user.domain.User;
import com.grap.user.dto.ResponseDto;
import com.grap.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController: save 호출됨");

        userService.signup(user);

        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
}