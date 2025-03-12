package com.api.proventus.service;

import com.api.proventus.domain.user.User;
import com.api.proventus.dto.login.LoginRequestDTO;
import com.api.proventus.infra.security.TokenService;
import com.api.proventus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public User login(LoginRequestDTO loginRequestDTO) {
        User user = this.userRepository.findUserByEmail(loginRequestDTO.email());
        if(passwordEncoder.matches( loginRequestDTO.password(),user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmail(username);
    }
}
