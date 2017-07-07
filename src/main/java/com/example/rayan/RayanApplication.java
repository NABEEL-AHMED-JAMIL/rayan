package com.example.rayan;

import com.example.rayan.entity.Doctor;
import com.example.rayan.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.example.rayan")
public class RayanApplication {

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Bean
//	CommandLineRunner init(final DoctorRepository doctorRepository) {
//
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... arg0) throws Exception {
//
//				doctorRepository.save(
//						new Doctor(1L, "nabeel.amd93@gmail.com", "nabeel", passwordEncoder.encode("ballistic"), "Nabeel", "Ahmed", true, null, null));
//			}
//
//		};
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(RayanApplication.class, args);
	}
}

