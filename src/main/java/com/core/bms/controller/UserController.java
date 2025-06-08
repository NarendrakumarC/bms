package com.core.bms.controller;

import com.core.bms.dto.ResponseStatus;
import com.core.bms.dto.SignUpRequestDTO;
import com.core.bms.dto.SignUpResponseDTO;
import com.core.bms.model.User;
import com.core.bms.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO signup(@RequestBody SignUpRequestDTO signUpRequestDTO){
       User user =  userService.signup(signUpRequestDTO.getName(),signUpRequestDTO.getEmail(),signUpRequestDTO.getPassword());
        SignUpResponseDTO  responseDTO = new SignUpResponseDTO();
        responseDTO.setUser(user);
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

}
