package com.api.proventus.controllers;

import com.api.proventus.domain.permission.Permission;
import com.api.proventus.domain.role.UserRoles;
import com.api.proventus.dto.permission.PermissionRequestDTO;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @PostMapping("/create")
    public ResponseEntity addRoleToUser(@RequestBody PermissionRequestDTO permissionRequestDTO) {
        Permission permission = this.permissionService.createPermission(permissionRequestDTO);

        if(permission == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
