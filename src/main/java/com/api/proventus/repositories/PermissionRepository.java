package com.api.proventus.repositories;

import com.api.proventus.domain.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
Optional<Permission> findByName(String name);
Optional<Permission> findByNameIgnoreCase(String name);
}
