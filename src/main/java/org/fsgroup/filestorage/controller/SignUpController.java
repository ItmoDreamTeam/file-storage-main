package org.fsgroup.filestorage.controller;

import org.fsgroup.filestorage.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin
public class SignUpController {

    @Resource
    private UserService service;

    @PostMapping
    public ResponseEntity signUp(@RequestParam String username, @RequestParam String password) {
        service.signUp(username, password);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
