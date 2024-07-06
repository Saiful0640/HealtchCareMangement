package com.doctormangment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityCOnfig {

    @Autowired
    private CustomeUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz ->authz
                .requestMatchers("/api/v1/login","/api/v1/doctor-login","/api/v1/patient-login").permitAll()
                .requestMatchers("/api/v1/doctors/**").hasRole("DOCTOR")
                .requestMatchers("/api/v1/patients/**").hasRole("PATIENT")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                 .defaultSuccessUrl("/dashboard", true)
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                );
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }



}
