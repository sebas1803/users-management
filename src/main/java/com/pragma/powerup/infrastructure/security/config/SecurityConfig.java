package com.pragma.powerup.infrastructure.security.config;

import com.pragma.powerup.infrastructure.security.jwt.JwtEntryPoint;
import com.pragma.powerup.infrastructure.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtFilter jwtFilter;


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/users/findById/{userId}").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/users/owner").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/users/employee").hasAuthority("ROLE_OWNER")
                .antMatchers(HttpMethod.POST, "/api/v1/users/client").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/findByEmail/{email}").permitAll()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
