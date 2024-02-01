package hk.tomihi.diningapi;

import hk.tomihi.diningapi.model.PasswordHashed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig   {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // TODO

            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(
                            "/user/login",
                            "/user/register",
                            "/restaurant",
                            "/login"
                    ).permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/**").hasRole("USER")
                   /* .requestMatchers(
                            "/reviews",
                            "/reviews/user/*",
                            "/reviews/my",
                            "/reviews/post",
                            "/reviews/restaurant/*",
                            "/admin",
                            "/admin/restaurant/add",
                            "/admin/approve/*",
                            "/restaurant",
                            "/user",
                            "/user/login",
                            "/user/register",
                            "/user/profile"
                    ).permitAll()*/
                    .anyRequest().authenticated()
            )
           // .logout(logout -> new LogoutConfigurer<>().permitAll().logoutUrl("/logout"))
        ;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordHashed();
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }*/

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withUserDetails(userDetailsS)
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }*/
}