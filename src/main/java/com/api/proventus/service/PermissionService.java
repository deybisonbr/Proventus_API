package com.api.proventus.service;

import com.api.proventus.domain.permission.Permission;
import com.api.proventus.domain.permission.RolePermission;
import com.api.proventus.domain.role.Role;
import com.api.proventus.domain.role.UserRoles;
import com.api.proventus.domain.user.User;
import com.api.proventus.dto.permission.AddPermissionToRole;
import com.api.proventus.dto.permission.PermissionRequestDTO;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.repositories.PermissionRepository;
import com.api.proventus.repositories.PermissionRolesRepository;
import com.api.proventus.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionRolesRepository permissionRolesRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public Permission createPermission(PermissionRequestDTO permissionRequestDTO) {
        Optional<Permission> permissionNameExists = permissionRepository.findByNameIgnoreCase(permissionRequestDTO.name());

        if(permissionNameExists.isPresent()) {
            return null;
        }

        Permission permission = new Permission();
        permission.setName(permissionRequestDTO.name().toUpperCase());
        permission.setDescription(permissionRequestDTO.description().toUpperCase());
        permission.setCreated_at(new Date());
        permission.setUpdated_at(new Date());

        return permissionRepository.save(permission);

    }

    public RolePermission addPermissionsToRole(AddPermissionToRole addPermissionToRole) {

        Permission permission = permissionRepository.findById(addPermissionToRole.permissionId())
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        Role role = rolesRepository.findById(addPermissionToRole.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

//        if (permissionRepository.existsByUserIdAndRoleId(user.getId(), role.getId())) {
//            return null;
//        }
//
        RolePermission addRoles = new RolePermission();
        addRoles.setPermission(permission);
        addRoles.setRole(role);
//
   return permissionRolesRepository.save(addRoles);
    }
}
