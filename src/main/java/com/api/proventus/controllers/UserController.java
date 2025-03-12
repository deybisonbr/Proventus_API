package com.api.proventus.controllers;

import com.api.proventus.domain.user.User;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.dto.user.RegisterRequestDTO;
import com.api.proventus.dto.user.UserPermissionsResponseDTO;
import com.api.proventus.dto.user.UserResponseDTO;
import com.api.proventus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/create")
    public ResponseEntity register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        User newuser = this.userService.createUser(registerRequestDTO);

        if(newuser == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name or email already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(newuser.getId(), newuser.getName(),newuser.getEmail() ));
    }

    @GetMapping("/{userId}/permissions")
    public ResponseEntity getUserPermissions(@PathVariable UUID userId) {
        UserPermissionsResponseDTO userPermissionsResponseDTO = this.userService.getUserPermissionsById(userId);

        if(userPermissionsResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userPermissionsResponseDTO);
    }
}
