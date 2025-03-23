package com.api.proventus.controllers;

import com.api.proventus.domain.notification.Notification;
import com.api.proventus.dto.notification.NotificationResponseDTO;
import com.api.proventus.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user")
    public ResponseEntity<ArrayList<NotificationResponseDTO>> getNotificationsByUser(@RequestHeader("Authorization") String authorizationHeader) {
        // Extrair o token JWT do cabeçalho "Authorization"
        String token = authorizationHeader.substring(7); // Remove "Bearer " do início

        ArrayList<NotificationResponseDTO> notifications = notificationService.getNotificationsByUser(token);

        return ResponseEntity.ok(notifications);
    }

}
