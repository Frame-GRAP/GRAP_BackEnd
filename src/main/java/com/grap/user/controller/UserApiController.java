//package com.grap.user.controller;
//
//import com.grap.user.domain.User;
//import com.grap.user.dto.UserResponseDto;
//import com.grap.user.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
//@CrossOrigin("*")
//@RestController
//public class UserApiController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @PostMapping("/api/user") //signup
//    public UserResponseDto<Integer> save(@RequestBody User user) {
//        System.out.println("UserApiController: save 호출됨");
//        userService.signup(user);
//        return new UserResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
//
//    @PostMapping("/api/user/login")
//    public UserResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController: login 호출됨");
//        User principal = userService.login(user); // principal = 접근주체
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        else
//            return new UserResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1);
//        return new UserResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
//}