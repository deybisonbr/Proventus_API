package com.api.proventus.dto.role;

import com.api.proventus.domain.role.Role;
import com.api.proventus.domain.user.User;

import java.util.UUID;

public record AddRoleToUserDTO(UUID userId, UUID roleId) {}