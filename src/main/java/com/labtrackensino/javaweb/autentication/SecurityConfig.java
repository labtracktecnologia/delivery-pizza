package com.labtrackensino.javaweb.autentication;

import com.labtrackensino.javaweb.autentication.filters.JWTAuthenticationFilter;
import com.labtrackensino.javaweb.autentication.filters.JWTAuthorizationFilter;
import com.labtrackensino.javaweb.autentication.security.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	private UserDetailsService userDetailsService;


	private Environment env;


	private JWTUtil jwtUtil;


	private BCryptPasswordEncoder pe;


	private static final String[] PUBLIC_MATCHERS_GET_SET = {
			"/bebida/**",
			"/borda/**",
			"/ingrediente/**",
			"/pedido/**",
			"/sabor/**",
			"/pizza/**"
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuario/**",
			"/auth/forgot/**"
	};

	private String[] swaggerWhiteList = {"/v2/api-docs",
			"/swagger-resources/configuration/ui",
			"/swagger-resources",
			"/swagger-resources/configuration/security",
			"/swagger-ui.html",
			"/webjars/**"};


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers(swaggerWhiteList).permitAll()
//				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
//				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET_SET).permitAll()
				.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
