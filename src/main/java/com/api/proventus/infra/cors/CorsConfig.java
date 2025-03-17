package com.api.proventus.infra.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                // Permitir apenas o frontend com origem localhost:5173
//                .allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")  // Permitir qualquer cabeçalho
//                .allowCredentials(true); // Permitir cookies (se necessário)
//    }

    @Bean
    public CorsFilter corsFilter() {
        // Configuração de CORS
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173");  // Permitir origem do frontend
        corsConfig.addAllowedMethod("*");  // Permitir todos os métodos HTTP (GET, POST, PUT, DELETE, OPTIONS)
        corsConfig.addAllowedHeader("*");  // Permitir qualquer cabeçalho
        corsConfig.setAllowCredentials(true);  // Permitir cookies se necessário

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Aplica a configuração a todas as rotas

        return new CorsFilter(source);  // Retorna o filtro CORS
    }
}
