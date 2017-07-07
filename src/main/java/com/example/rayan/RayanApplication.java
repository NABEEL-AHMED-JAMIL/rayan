package com.example.rayan;

//http://www.baeldung.com/rest-template
//http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/

import com.example.rayan.entity.Doctor;
import com.example.rayan.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



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
//						new Doctor(1L, "nabeel.amd93@gmail.com", "nabeel", passwordEncoder.encode("ballistic"), "Nabeel", "Ahmed",true,true, null, null));
//			}
//
//		};
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(RayanApplication.class, args);
	}
}

