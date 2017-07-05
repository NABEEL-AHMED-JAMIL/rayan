package com.example.rayan;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
		http.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/test/getPakistan").permitAll().
				antMatchers(HttpMethod.GET, "/account/private").hasAnyRole("ADMIN","DBA").
				antMatchers(HttpMethod.GET, "/admin/private").hasAuthority("ADMIN").
				antMatchers(HttpMethod.GET, "/dba/private").hasAuthority("DBA").
				antMatchers(HttpMethod.GET, "/user/private").hasAuthority("USER").
				and()
				.formLogin().loginPage("/account/login").permitAll().and().
				csrf().disable();
	}

}
