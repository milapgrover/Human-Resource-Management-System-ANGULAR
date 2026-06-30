    package com.example.hrms.config;
    import com.example.hrms.security.JwtAuthenticationFilter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpMethod;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @RequiredArgsConstructor
    public class SecurityConfig {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
        {
            httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth-> auth
                            .requestMatchers("/auth/**").permitAll()

                            .requestMatchers(HttpMethod.GET , "/employees/**")
                            .hasAnyRole("ADMIN" , "EMPLOYEE")

                            .requestMatchers(HttpMethod.POST, "/employees/**")
                            .hasRole("ADMIN")

                            .requestMatchers(HttpMethod.PUT, "/employees/**")
                            .hasRole("ADMIN")

                            .requestMatchers(HttpMethod.DELETE, "/employees/**")
                            .hasRole("ADMIN")

                            .requestMatchers("/attendance/**")
                            .hasAnyRole("ADMIN", "EMPLOYEE")

                            .requestMatchers(HttpMethod.GET, "/leaves/**")
                            .hasAnyRole("ADMIN", "EMPLOYEE")

                            .requestMatchers(HttpMethod.POST, "/leaves/**")
                            .hasAnyRole("ADMIN", "EMPLOYEE")

                            .requestMatchers(HttpMethod.PUT, "/leaves/approve/**")
                            .hasRole("ADMIN")

                            .requestMatchers(HttpMethod.PUT, "/leaves/reject/**")
                            .hasRole("ADMIN")

                            .requestMatchers(HttpMethod.GET, "/payroll/**")
                            .hasAnyRole("ADMIN", "EMPLOYEE")

                            .requestMatchers(HttpMethod.POST, "/payroll/**")
                            .hasRole("ADMIN")

                            .anyRequest().authenticated()
                    )
                    .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception
        {
            return authenticationConfiguration.getAuthenticationManager();
        }
        @Bean
        public PasswordEncoder passwordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
    }