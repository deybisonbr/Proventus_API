package com.api.proventus.dto.notification;

public record NotificationRequestDTO(String title, String description, Boolean read, String type, String url) {
}
