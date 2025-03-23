package com.api.proventus.dto.permission;

import java.util.UUID;

public record AddPermissionToRole(UUID roleId, UUID permissionId) {
}
