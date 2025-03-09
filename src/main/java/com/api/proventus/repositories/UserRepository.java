package com.api.proventus.repositories;

import com.api.proventus.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

//    @Query("SELECT u FROM User u " +
//            "WHERE u.email = :email")
//    public User findByEmail(String email);

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    Optional<User> findByUsername(String username);
}
