package com.aziz.ecommercerestapi.config;

import com.aziz.ecommercerestapi.security.JwtAuthenticationEntryPoint;
import com.aziz.ecommercerestapi.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JwtAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired
  private JwtAuthenticationFilter authenticationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//            .authorizeHttpRequests((authorize) ->
//                    authorize
//                            .requestMatchers(HttpMethod.GET,"/api/v1/**").permitAll()
//                            .requestMatchers("/api/auth/**").permitAll()
//                            .anyRequest().permitAll()
//            ).httpBasic(Customizer.withDefaults());
    http.csrf().disable()
            .authorizeHttpRequests((authorize) ->
                    authorize
                            .anyRequest().permitAll()
            ).exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
