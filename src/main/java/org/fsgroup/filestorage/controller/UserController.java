package org.fsgroup.filestorage.controller;

import org.fsgroup.filestorage.model.User;
import org.fsgroup.filestorage.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> get(Authentication auth) {
        return new ResponseEntity<>(userService.get(auth.getName()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity edit(Authentication auth, @RequestParam String password) {
        userService.edit(auth.getName(), password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(Authentication auth) {
        userService.delete(auth.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
