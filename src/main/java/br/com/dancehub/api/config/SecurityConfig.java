package br.com.dancehub.api.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
//          Referente ao Swagger
            "/docs/api-docs",
            "/docs/swagger.html",
//          Spring Actuator
            "/actuator/**",
            "/health/**",
//          Api endpoints
            "/v1/sign/**",
            "/docs/**",
            "/error",
            "/error/*",
            "/v1/events/*",
            "/v1/events/list",
            "/v1/subscription/*",
            "/v1/subscription/list",
            "/v1/subscription/event/*",
    };


    private final AuthenticationProvider authenticationProvider;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
        cors.addAllowedMethod(HttpMethod.POST);
        cors.addAllowedMethod(HttpMethod.PUT);
        cors.addAllowedMethod(HttpMethod.PATCH);
        cors.addAllowedMethod(HttpMethod.GET);
        cors.addAllowedMethod(HttpMethod.DELETE);
        http.addFilterAfter(new JwtAuthFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(config -> config.configurationSource(request -> cors))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().permitAll()
                ).sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(this.authenticationProvider)
                .exceptionHandling(config -> config.authenticationEntryPoint(this.authEntryPointJwt));
        return http.build();
    }


}
