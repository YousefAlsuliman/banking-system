package com.microservices.user_service.controller;

import com.microservices.user_service.models.dto.response.UserDetailsResponse;
import com.microservices.user_service.models.entity.UserEntity;
import com.microservices.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    private ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @Valid @RequestBody UserEntity user){
        return ResponseEntity.ok(this.userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }

    @GetMapping("details/{id}")
    private ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUserDetails(id));
    }
}
