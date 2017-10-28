package org.fsgroup.filestorage.server.controller;

import org.fsgroup.filestorage.server.model.User;
import org.fsgroup.filestorage.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/{username}")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> get(@PathVariable String username) {
        return new ResponseEntity<>(userService.get(username), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity edit(@PathVariable String username, @RequestParam String password) {
        userService.edit(username, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable String username) {
        userService.delete(username);
        return new ResponseEntity(HttpStatus.OK);
    }
}
