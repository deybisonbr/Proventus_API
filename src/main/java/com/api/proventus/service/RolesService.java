package com.api.proventus.service;

import com.api.proventus.domain.role.Role;
import com.api.proventus.domain.role.UserRoles;
import com.api.proventus.domain.user.User;
import com.api.proventus.dto.role.RoleRequestDTO;
import com.api.proventus.dto.role.AddRoleToUserDTO;
import com.api.proventus.repositories.RolesRepository;
import com.api.proventus.repositories.UserRepository;
import com.api.proventus.repositories.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserRepository userRepository;

    public Role createRole(RoleRequestDTO roleRequestDTO) {
        Optional<Role> roleNameExists = this.rolesRepository.findByName(roleRequestDTO.name());

        if(roleNameExists.isPresent()) {
            return null;
        }

        Role newRole = new Role();
        newRole.setName(roleRequestDTO.name().toUpperCase());
        newRole.setDescription(roleRequestDTO.description());
        newRole.setCreatedAt(new Date());

        this.rolesRepository.save(newRole);
        return newRole;
    }

    public UserRoles addRoleToUser(AddRoleToUserDTO addRoleToUserDTO) {

        User user = userRepository.findById(addRoleToUserDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = rolesRepository.findById(addRoleToUserDTO.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (userRolesRepository.existsByUserIdAndRoleId(user.getId(), role.getId())) {
            return null;
        }

        UserRoles addRoles = new UserRoles();
        addRoles.setUser(user);
        addRoles.setRole(role);

        return userRolesRepository.save(addRoles);
    }

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        this.rolesRepository.findAll().forEach(roles::add);
        return roles;
    }
}
