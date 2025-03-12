package com.api.proventus.dto.user;

import java.util.List;
import java.util.UUID;

public record UserPermissionsResponseDTO(UUID userId, List<String> permissions) {
}
