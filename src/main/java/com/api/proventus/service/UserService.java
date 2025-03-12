package com.api.proventus.service;

import com.api.proventus.domain.role.Role;
import com.api.proventus.domain.user.User;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.dto.user.RegisterRequestDTO;
import com.api.proventus.repositories.RolesRepository;
import com.api.proventus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public User createUser(RegisterRequestDTO registerRequestDTO) {
        Optional<User> emailExists = this.userRepository.findByEmail(registerRequestDTO.email());
        Optional<User> usernameExists = this.userRepository.findByUsername(registerRequestDTO.username());

        if(emailExists.isPresent() || usernameExists.isPresent()) {
            return null;
        }

        User newUser = new User();
        newUser.setName(registerRequestDTO.name());
        newUser.setUsername(registerRequestDTO.username());
        newUser.setEmail(registerRequestDTO.email());
        newUser.setIsActive(true);
        newUser.setCreatedAt(new Date());

        newUser.setPassword(passwordEncoder.encode(registerRequestDTO.password()));

        this.userRepository.save(newUser);
        return newUser;
    }

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("User not found with ID: " + id);  // Lançando exceção caso o usuário não seja encontrado
    }

}
