package com.grap.user.controller;

import com.grap.favor.dto.FavorListResponseDto;
import com.grap.user.config.auth.LoginUser;
import com.grap.user.config.auth.dto.SessionUser;
import com.grap.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;


}