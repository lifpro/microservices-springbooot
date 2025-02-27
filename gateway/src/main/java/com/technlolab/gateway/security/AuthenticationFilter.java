package com.technlolab.gateway.security;

import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtUtil jwtUtil;
    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
        // Configuration si nécessaire
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.parseJwtClaims(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();

            // Vérifie si le header Authorization est présent
            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            // Vérifie si le token commence par "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Extraire le token sans "Bearer "
            String token = authHeader.substring(7);

            System.out.println((token));

            // TODO : Vérifier et valider le token JWT ici
//            Claims claims = jwtUtil.parseJwtClaims(token);
//
//            if(claims != null & jwtUtil.validateClaims(claims)){
//                String email = claims.getSubject();
//                System.out.println("email : "+email);
//                Authentication authentication =
//                        new UsernamePasswordAuthenticationToken(email,"",new ArrayList<>());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }


            // Valider le token JWT
            if (jwtUtil.isInvalid(token)) {
                return this.onError(exchange, "Invalid JWT Token", HttpStatus.UNAUTHORIZED);
            }

            // Ajouter les claims du token aux en-têtes de la requête
            this.populateRequestWithHeaders(exchange, token);
            return chain.filter(exchange);
        };
    }
}
