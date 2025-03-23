package com.api.proventus.repositories;

import com.api.proventus.domain.permission.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRolesRepository extends JpaRepository<RolePermission, UUID> {
}
