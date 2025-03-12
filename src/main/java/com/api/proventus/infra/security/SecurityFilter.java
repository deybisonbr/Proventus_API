package com.api.proventus.infra.security;


import com.api.proventus.domain.user.User;
import com.api.proventus.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;

    public SecurityFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//        var login = tokenService.validateToken(token);
//
//        if(login != null) {
//            User user = userService.findByEmail(login);
////            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//            var authorities = user.getAuthorities();;
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var userId = tokenService.validateToken(token);  // Pega o ID do usuário do token

        if (userId != null) {
            // Recupera o usuário do banco de dados usando o ID
            User user = userService.findById(UUID.fromString(userId));

            // Carrega as authorities do usuário com base nas roles no banco
            var authorities = user.getAuthorities();

            // Cria a autenticação com todas as authorities do usuário
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7); // Remove "Bearer " do token
    }
}
