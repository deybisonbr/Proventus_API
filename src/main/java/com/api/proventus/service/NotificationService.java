package com.api.proventus.service;

import com.api.proventus.domain.notification.Notification;
import com.api.proventus.dto.notification.NotificationResponseDTO;
import com.api.proventus.infra.security.TokenService;
import com.api.proventus.repositories.NotificationRepository;
import com.api.proventus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public ArrayList<NotificationResponseDTO> getNotificationsByUser(String token) {

        String userIdString = tokenService.validateToken(token);

        if (userIdString == null) {
            throw new RuntimeException("invalid token");
        }

        UUID userId = UUID.fromString(userIdString);

        // 3. Buscar as notificações do usuário no repositório
        ArrayList<NotificationResponseDTO> notifications = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Notification> notificationList = notificationRepository.findNotificationsByUserId(userId, pageRequest);

        // 4. Mapear cada notificação para o DTO e adicionar à lista
        for (Notification notification : notificationList) {
            NotificationResponseDTO dto = new NotificationResponseDTO(
                    notification.getId(),
                    notification.getTitle(),
                    notification.getDescription(),
                    notification.getRead(),
                    notification.getType(),
                    notification.getUrl(),
                    notification.getCreated_at(),
                    notification.getUser()
            );

            notifications.add(dto);
        }

        // 5. Retornar a lista de DTOs
        return notifications;
    }



}
