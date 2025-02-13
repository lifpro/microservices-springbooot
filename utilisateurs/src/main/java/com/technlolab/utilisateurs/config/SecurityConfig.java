package com.technlolab.utilisateurs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Désactiver CSRF (à activer si nécessaire)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth").permitAll()  // Accès public
                        .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN") // Seulement pour ADMIN
                        .anyRequest().authenticated()  // Toutes les autres requêtes nécessitent une authentification
                )
                .httpBasic(Customizer.withDefaults()); // Activer HTTP Basic Auth

        return http.build();
    }
}
