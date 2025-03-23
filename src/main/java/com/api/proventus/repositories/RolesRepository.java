package com.api.proventus.repositories;

import com.api.proventus.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(String name);
    Optional<Role> findByNameIgnoreCase(String name);
    ArrayList<Role> findAll();
}
