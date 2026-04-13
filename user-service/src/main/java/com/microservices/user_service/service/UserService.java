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

    public UserEntity getUser(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("user not found!"));
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> UserEntities = this.userRepository.findAll();
        if (UserEntities.isEmpty()) {
            throw new RuntimeException("no users available!");
        }
        return this.userRepository.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        this.userRepository.save(user);
        return user;
    }

    public UserEntity updateUser(Long id, @Valid UserEntity user) {
        Optional<UserEntity> targetUser = this.userRepository.findById(id);
        UserEntity updatedUser = targetUser.map(t -> {
            t.setName(user.getName());
            t.setEmail(user.getEmail());
            t.setPhone(user.getPhone());
            this.userRepository.save(t);
            return t;
        }).orElseThrow(() -> new RuntimeException("no user with id: " + id));
        return updatedUser;
    }

    public String deleteUser(Long id) {
        this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user with id: " + id));
        this.userRepository.deleteById(id);
        return "user deleted successfully!";
    }

    public UserDetailsResponse getUserDetails(Long id){
        UserEntity user = getUser(id);
        List<AccountResponse> accounts = this.accountClient.getAccountsByUserId(id).getBody();

        return new UserDetailsResponse(user, accounts);
    }
}
