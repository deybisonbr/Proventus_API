package com.api.proventus.repositories;

import com.api.proventus.domain.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {
    boolean existsByUserIdAndRoleId(UUID userId, UUID roleId);
}
