package com.chiru.springsecurity.controller;

import com.chiru.springsecurity.models.UserAuthDTO;
import com.chiru.springsecurity.models.UserLoginResponseDTO;
import com.chiru.springsecurity.models.UserType;
import com.chiru.springsecurity.service.AuthenticationService;
import com.chiru.springsecurity.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chiranjeevi
 */
@RestController
@RequestMapping("/auth/user")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(@RequestBody UserAuthDTO userAuthDTO){
        try {
            return userService.regUser(userAuthDTO);
        }catch (BadRequestException e){
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponseDTO userLogin(@RequestBody UserAuthDTO userAuthDTO){
        authenticationService.authenticate(UserType.USER,userAuthDTO.getUserName(),userAuthDTO.getPassword());
        return userService.loginUser(userAuthDTO);
    }
}
