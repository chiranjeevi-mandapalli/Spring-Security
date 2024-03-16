package com.chiru.springsecurity.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chiranjeevi
 */
@RestController
@RequestMapping("/api/public/user")
public class BasicRestUserApi {
    @GetMapping("/")
    public ResponseEntity<String> userHome(Authentication authentication){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
