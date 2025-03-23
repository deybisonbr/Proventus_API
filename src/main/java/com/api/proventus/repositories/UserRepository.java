package com.api.proventus.repositories;

import com.api.proventus.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

//    @Query("SELECT u FROM User u " +
//            "WHERE u.email = :email")
//    public User findByEmail(String email);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    Optional<User> findByUsername(String username);
//    UserDetails findByLogin(String email);
}
