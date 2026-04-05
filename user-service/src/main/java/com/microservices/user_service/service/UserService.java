package com.microservices.user_service.service;

import com.microservices.user_service.models.entity.UserEntity;
import com.microservices.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserEntity> getUser(Long userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        return user.map(u -> ResponseEntity.ok(u)).orElseThrow(() -> new RuntimeException("user not found!"));
    }

    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> UserEntities = this.userRepository.findAll();
        if (UserEntities.isEmpty()) {
            throw new RuntimeException("no users available!");
        }
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    public ResponseEntity<UserEntity> createUser(UserEntity user) {
        this.userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<UserEntity> updateUser(Long userId, @Valid UserEntity user) {
        Optional<UserEntity> targetUser = this.userRepository.findById(userId);
        UserEntity updatedUser = targetUser.map(t -> {
            t.setName(user.getName());
            t.setEmail(user.getEmail());
            t.setPhone(user.getPhone());
            this.userRepository.save(t);
            return t;
        }).orElseThrow(() -> new RuntimeException("no user with id: " + userId));
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<String> deleteUser(Long userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("no user with id: " + userId));
        this.userRepository.deleteById(userId);
        return ResponseEntity.ok("user deleted successfully!");
    }
}
