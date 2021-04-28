package com.grap.user.exception;

import com.grap.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public UserResponseDto<String> handleArgumentException(Exception e) {
        return new UserResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Already exists");
    }
}
