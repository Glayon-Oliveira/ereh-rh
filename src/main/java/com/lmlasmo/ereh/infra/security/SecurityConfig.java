package com.lmlasmo.ereh.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lmlasmo.ereh.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity sec, JwtAuthenticationFilter jwtFilter) throws Exception {
		
		return sec.csrf(csrf -> csrf.disable())
				  .formLogin(f -> f.disable())
				  .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				  .authorizeHttpRequests(r -> r.requestMatchers("/login")
						  .permitAll()
						  .anyRequest().authenticated())
				  .httpBasic(Customizer.withDefaults())
				  .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
				  .build();				
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
