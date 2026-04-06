package com.microservices.user_service.service;

import com.microservices.user_service.models.client.AccountClient;
import com.microservices.user_service.models.dto.response.AccountResponse;
import com.microservices.user_service.models.dto.response.UserDetailsResponse;
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
    @Autowired
    private AccountClient accountClient;

    public ResponseEntity<UserEntity> getUser(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
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

    public ResponseEntity<UserEntity> updateUser(Long id, @Valid UserEntity user) {
        Optional<UserEntity> targetUser = this.userRepository.findById(id);
        UserEntity updatedUser = targetUser.map(t -> {
            t.setName(user.getName());
            t.setEmail(user.getEmail());
            t.setPhone(user.getPhone());
            this.userRepository.save(t);
            return t;
        }).orElseThrow(() -> new RuntimeException("no user with id: " + id));
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<String> deleteUser(Long id) {
        this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user with id: " + id));
        this.userRepository.deleteById(id);
        return ResponseEntity.ok("user deleted successfully!");
    }

    public ResponseEntity<UserDetailsResponse> getUserDetails(Long id){
        UserEntity user = getUser(id).getBody();
        List<AccountResponse> accounts = this.accountClient.getAccountsByUserId(id).getBody();

        return ResponseEntity.ok(new UserDetailsResponse(user, accounts));
    }
}
