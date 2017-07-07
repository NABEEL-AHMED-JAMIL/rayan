package com.example.rayan.security;

import com.example.rayan.entity.Doctor;
import com.example.rayan.entity.Role;
import com.example.rayan.repository.DoctorRepository;
import com.example.rayan.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@Service("userService")
class WebSecurityConfiguration  implements UserDetailsService {

	@Autowired
	DoctorRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("----------------------------------------");
		System.out.println("-------"+username+"---------------------");
		Doctor account = accountRepository.findByUsername(username);
		// getting the role
		System.out.println(account.toString());
		System.out.println("------------------End------------------");

		if(account != null) {
			System.out.println("------------------Doctor not null------------------");
			return new UserDetails()  {

				// ---1
				@Override
				public boolean isAccountNonLocked() {
					System.out.println("------------------AccountNonLocked is true------------------");
					return true;
				}

				// -----2
				@Override
				public boolean isEnabled() {
					System.out.println("------------------Enable is true------------------");
					return account.getEnable();
				}

				// ------3
				@Override
				public boolean isAccountNonExpired() {
					System.out.println("------------------AccountNonExpired is true------------------");
					return true;
				}

				// ------4
				@Override
				public String getPassword() {
					System.out.println("------------------PassWord is getting------------------");
					return account.getPassword();
				}

				// ------5
				@Override
				public boolean isCredentialsNonExpired() {
					System.out.println("------------------CredentialsNonExpired is true------------------");
					return true;
				}

				// ------6
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					System.out.println("------------------InSide the Authorities------------------");
					System.out.println("----------------------------------------");
					int i = 0;
					Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
					System.out.println("----------Checking the auth set size ---------"+auths.size()+"----------------");
					for(Role role: account.getRoles()){
						System.out.println("---------------"+role+"-------------"+ i++ +"------------");
						auths.add(new SimpleGrantedAuthority(role.getRole()));
						System.out.println("---------------"+auths.toString()+"-------------"+ auths.size() +"------------");
					}
					return new ArrayList<GrantedAuthority>(auths);

				}

				@Override
				public String getUsername() {
					return account.getUsername();
				}
			};

		} else {
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}
