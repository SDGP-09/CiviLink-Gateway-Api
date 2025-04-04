package com.civilink.civilink_gateway_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

        // /** to be change
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow preflight requests
                        .pathMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/messages/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/messages/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/messages/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/contractors/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/dealsIndeed/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/blablabla/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/tasks/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/tasks/**").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/api/tasks/**").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/api/tasks/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/tenders/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/tenders/**").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/api/tenders/**").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/api/tenders/**").permitAll()
                                   
                        .pathMatchers(HttpMethod.POST, "/api/v1/images/**").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/api/v1/images/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/ratings/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/ratings/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/public-project-display/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/public-project-display/**").permitAll()
                        
                        .pathMatchers("/eureka/*").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        return serverHttpSecurity.build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //need to change allowed origins on finalizing the requests

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }

}
