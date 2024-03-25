package org.nurseitkalbaev.carmatch.security;
import org.nurseitkalbaev.carmatch.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * SecurityConfiguration class completely refactored
 * IMPORTANT:
 * if you are going to use for specific endpoint more than one user role
 * always use hasAnyRole(...)
 * In order to be customizable always with Thymeleaf successForwardUrl(...) method */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private UserServiceImpl userDetailsService;
//@Autowired
private final  UserDetailsService userDetailsService;
@Autowired
public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/", "/login*","/vehicles/","/vehicles/listings","/vehicles/carDetails",
                                        "/css/*", "/js/*", "/signup", "/signup-process").permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // should point to login page
                        .successForwardUrl("/") // must be in order thymeleaf security extras work
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()

                )
                .sessionManagement(session -> session
                        .maximumSessions(1) // Allow only one session per user
                        .maxSessionsPreventsLogin(false) // Allow multiple logins
                        .expiredUrl("/login?expired")); // Redirect to login page if session expired
        return http.build();

    }
}