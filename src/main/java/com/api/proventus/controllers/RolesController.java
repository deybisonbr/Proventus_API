package com.api.proventus.controllers;

import com.api.proventus.domain.role.Role;
import com.api.proventus.domain.role.UserRoles;
import com.api.proventus.domain.user.User;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.dto.role.RoleRequestDTO;
import com.api.proventus.dto.role.RoleResponseDTO;
import com.api.proventus.dto.user.RegisterRequestDTO;
import com.api.proventus.dto.user.UserResponseDTO;
import com.api.proventus.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/role")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @PostMapping("/create")
    public ResponseEntity createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        Role newRole = this.rolesService.createRole(roleRequestDTO);

        if(newRole == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Name or email already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new RoleResponseDTO(newRole.getId(), newRole.getName(), newRole.getDescription()));
    }

    @PostMapping("/add")
    public ResponseEntity addRoleToUser(@RequestBody AddRoleToUserDTO addRoleToUserDTO) {
        UserRoles userRoles = this.rolesService.addRoleToUser(addRoleToUserDTO);

        if(userRoles == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping()
    public ResponseEntity getRoles() {
        List<Role> roles = this.rolesService.getRoles();
        if(roles == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }
}
