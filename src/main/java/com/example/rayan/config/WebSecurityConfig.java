package com.example.rayan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	private static String REALM="MY_Ballistic_Test";

	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;

	@Autowired
    PasswordEncoder passwordEncoder;


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//,"USER"
		http.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/patient/**").permitAll()
				.antMatchers(HttpMethod.GET, "/doctor/doctors").permitAll()
				.antMatchers(HttpMethod.GET, "/doctor/getDoctor").permitAll()
				.antMatchers(HttpMethod.GET, "/docType/**").permitAll()
				.antMatchers(HttpMethod.POST, "/docType").hasAuthority("ADMIN").
				antMatchers(HttpMethod.DELETE, "/docType/**").hasAuthority("ADMIN").
				antMatchers(HttpMethod.PUT, "/docType/**").hasAuthority("ADMIN").
				antMatchers("/note/addNote/**").hasAuthority("ADMIN").
				antMatchers(HttpMethod.POST, "/patient/addPatient").hasAuthority("ADMIN").
				antMatchers(HttpMethod.DELETE, "/patient/**").hasAuthority("ADMIN").
				antMatchers(HttpMethod.PUT, "/patient/**").hasAuthority("ADMIN").
				antMatchers(HttpMethod.POST, "/register").hasAuthority("ADMIN").
				and()
				.formLogin().loginPage("/account/login").permitAll().and().
				csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
	}


	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}

	/* To allow Pre-flight [OPTIONS] request from browser */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


	public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

		@Override
		public void commence(final HttpServletRequest request,
							 final HttpServletResponse response,
							 final AuthenticationException authException) throws IOException, ServletException {
			//Authentication failed, send error response.
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

			PrintWriter writer = response.getWriter();
			writer.println("HTTP Status 401 : " + authException.getMessage());
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			setRealmName("MY_Ballistic_Test");
			super.afterPropertiesSet();
		}
	}




}
