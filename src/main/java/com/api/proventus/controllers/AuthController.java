package com.api.proventus.controllers;

import com.api.proventus.domain.user.User;
import com.api.proventus.dto.error.ErrorResponseDTO;
import com.api.proventus.dto.login.LoginRequestDTO;
import com.api.proventus.dto.login.LoginResponseDTO;
import com.api.proventus.dto.login.LoginUserData;
import com.api.proventus.dto.notification.NotificationResponseDTO;
import com.api.proventus.infra.security.TokenService;
import com.api.proventus.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) {

        if(loginRequestDTO.email() == null || loginRequestDTO.password() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(401, "email or password cannot be empty"));
        }

        User user = this.authService.login(loginRequestDTO);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(401, "incorrect email or password"));
        }

        String token = this.tokenService.genereteToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(user.getId(),token));


    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if (tokenService.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        return ResponseEntity.ok("valid token");
    }

    @GetMapping("/user")
    public ResponseEntity<LoginUserData> getNotificationsByUser(@RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token JWT do cabeçalho "Authorization"
        String token = authorizationHeader.substring(7); // Remove "Bearer " do início

        LoginUserData loginUserData = authService.userDataByToken(token);

        return ResponseEntity.ok(loginUserData);
    }
}
