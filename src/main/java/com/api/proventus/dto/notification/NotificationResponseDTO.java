package com.api.proventus.dto.notification;

import com.api.proventus.domain.user.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record NotificationResponseDTO(UUID id, String title, String description, Boolean read, String type, String url, LocalDateTime created_at, User user) {

}
