package com.splitwise.api_gateway.filter;

import com.splitwise.api_gateway.JwtService;
import com.splitwise.api_gateway.exception.AuthenticationException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Login Request : {} ", exchange.getRequest().getURI());

            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("AuthToken");
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer")) {
                log.error("Authorization token not found");
                throw new AuthenticationException("Authorization token not found");
            }

            final String token = tokenHeader.split("Bearer ")[1];
            try {
                String userId = jwtService.getUserIdFromToken(token);
                ServerWebExchange modifiedExchange = exchange.mutate()
                        .request(r -> r.header("user_id", userId))
                        .build();
                return chain.filter(modifiedExchange);
            } catch (JwtException e) {
                log.error("JWT Exception : {}", e.getLocalizedMessage());
                throw new AuthenticationException("Invalid JWT token");
            }
        };
    }

    public static class Config {
    }
}