package com.microservices.user_service.models.dto.response;

import com.microservices.user_service.models.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse{

    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<AccountResponse> accounts;

    public UserDetailsResponse(UserEntity user, List<AccountResponse> accounts) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.accounts = accounts;
    }
}
