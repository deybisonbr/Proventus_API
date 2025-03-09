package com.api.proventus.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private String username;
    private  String email;
    private String password;
    private Boolean isActive;
    private Date updatedAt;
    private Date createdAt;
    private String updatedBy;
    private String createdBy;
}
