package com.api.proventus.repositories;

import com.api.proventus.domain.notification.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    ArrayList<Notification> findAllByUserId(UUID userId);

    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId OR n.user.id IS NULL ORDER BY n.created_at DESC")
    List<Notification> findNotificationsByUserId(@Param("userId") UUID userId, Pageable pageable);
}
