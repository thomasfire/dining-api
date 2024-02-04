package hk.tomihi.diningapi.config;

import hk.tomihi.diningapi.model.PasswordHashed;
import hk.tomihi.diningapi.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(
                                "/user/login",
                                "/user/register",
                                "/restaurant"
                        ).permitAll()
                        .requestMatchers(
                                "/admin/restaurant/add",
                                "/admin/approve/*"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "/user/profile",
                                "/reviews/user/*",
                                "/reviews/post",
                                "/reviews/restaurant/*",
                                "/reviews/my"
                        ).hasRole("USER")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordHashed();
    }
}