package com.cda.PayYouPayMe.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cda.PayYouPayMe.repository.UtilisateurRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            ).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
			auth.requestMatchers("/contact/**", "/message/**", "/transaction/**", "/transfert/**", "/utilisateur/**", "/me/user/**").hasAnyRole("USER", "ADMIN");
			//auth.requestMatchers("/user").hasAnyRole("ADMIN", "USER");
			auth.requestMatchers("/signUp", "/", "/login").permitAll();
			auth.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();
	}
	
	 /*
	   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf
	                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
	            )
	            .headers(headers -> headers
	                .frameOptions(frameOptions -> frameOptions.sameOrigin())
	            )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
	                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(Customizer.withDefaults())
	            .logout(logout -> logout
	                    .logoutUrl("/logout")                    
	                    .logoutSuccessUrl("/")
	                    .deleteCookies("JSESSIONID")
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                    .permitAll()
	                );
	            
	        return http.build();
	 
	
	*/
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

}