package com.humanbooster.Newbiz.config;

import com.humanbooster.Newbiz.services.SpringAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SpringAuthService springAuthService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/categories/{categoryId}/annonces", "/", "/annonce/list",
                                "/annonce/list/{id}", "/login", "/register", "error/**", "/js/**",
                                "css/**", "images/**").permitAll()
                        .requestMatchers(
                                "/annonce/add", "/annonce/edit/**", "/annonce/delete/**")
                        .hasAuthority("Journaliste")
                        .anyRequest().authenticated())
                .formLogin((form)->form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true))
                .logout((logout)->logout.logoutUrl("/logout"));

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(springAuthService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());


        return daoAuthenticationProvider;
    }
}
