package com.api.proventus.repositories;

import com.api.proventus.domain.role.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {
    boolean existsByUserIdAndRoleId(UUID userId, UUID roleId);
}
