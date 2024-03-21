package com.example.demo.config;

import com.example.demo.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.demo.user.Permission.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/api/v1/demo-controller/greetVisitor"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demo-controller/greet/admin","GET")).hasAnyAuthority(ADMIN_FULL_ACCESS.getPermission())
                                .requestMatchers(new AntPathRequestMatcher( "/api/v1/demo-controller/greet/adminOrUser","GET")).hasAnyAuthority(ADMIN_FULL_ACCESS.getPermission(), USER_READ.getPermission())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                /*http
                .csrf()
                .disable()
                .authorizeHttpRequests(WHITE_LIST_URL)
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                 //.hasAnyRole(ADMIN.name(), MANAGER.name())
                 .anyRequest()
                 .authenticated()
                .and()
                .sessionManagement()
                        .sessionCreationPolicy(STATELESS)
                        .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);*/
        return http.build();
    }
}
/*
.requestMatchers( new AntPathRequestMatcher("/api/v1/demo-controller/greet/admin")).hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demo-controller/greet/admin","GET")).hasAnyAuthority(ADMIN_FULL_ACCESS.getPermission())
                                .requestMatchers( new AntPathRequestMatcher("/api/v1/demo-controller/greet/adminOrUser")).hasAnyRole(Role.ADMIN.name(),Role.USER.name())
                                .requestMatchers(new AntPathRequestMatcher( "/api/v1/demo-controller/greet/adminOrUser","GET")).hasAnyAuthority(ADMIN_FULL_ACCESS.name(), USER_READ.name())



 */