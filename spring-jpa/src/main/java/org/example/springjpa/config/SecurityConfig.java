package org.example.springjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
        manager.createUser(
            User.withUsername("staff")
                    .password(passwordEncoder().encode("staff"))
                    .roles("STAFF")
                    .build()
        );
        manager.createUser(
            User.withUsername("patient")
                    .password(passwordEncoder().encode("patient"))
                    .roles("PATIENT")
                    .build()
        );
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity; enable in production
                .authorizeRequests()
                .antMatchers("/api/users/**").hasAnyRole("STAFF", "ADMIN","PATIENT") // Allow both USER and ADMIN roles
                .antMatchers("/api/rooms/all", "/api/rooms/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // Enable Basic Authentication
    }
}
