package com.nmp.restapi.controller;

import com.nmp.restapi.entity.User;
import com.nmp.restapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<String> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUser(userId).getUsername(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
