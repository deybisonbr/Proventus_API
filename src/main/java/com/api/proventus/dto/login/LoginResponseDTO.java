package com.api.proventus.dto.login;

import java.util.UUID;

public record LoginResponseDTO(UUID user_id, String access_token) {

}
