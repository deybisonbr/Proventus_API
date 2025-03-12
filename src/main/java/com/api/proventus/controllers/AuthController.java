package com.api.proventus.controllers;

import com.api.proventus.domain.user.User;
import com.api.proventus.dto.login.LoginRequestDTO;
import com.api.proventus.dto.login.LoginResponseDTO;
import com.api.proventus.infra.security.TokenService;
import com.api.proventus.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) {

        User user = this.authService.login(loginRequestDTO);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or Password is incorrect");
        }

        String token = this.tokenService.genereteToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(user.getId(),token));


    }
}
