package com.microservices.user_service.configuration;

import com.microservices.user_service.models.entity.UserEntity;
import com.microservices.user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository){
        return args -> {

            userRepository.save(new UserEntity(null, "Ahmed Ali", "ahmed@gmail.com", "0551111111"));
            userRepository.save(new UserEntity(null, "Sara Mohammed", "sara@gmail.com", "0552222222"));
            userRepository.save(new UserEntity(null, "Fahad Abdullah", "fahad@gmail.com", "0553333333"));
            userRepository.save(new UserEntity(null, "Noura Khalid", "noura@gmail.com", "0554444444"));

            System.out.println("Dummy users loaded!");
        };
    }
}
