package com.example.rayan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	AccountRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("----------------------------------------");
		System.out.println("-------"+username+"---------------------");
		Account account = accountRepository.findByUsername(username);
		// getting the role

		System.out.println(account.toString());
		System.out.println(account.getId()+"--------"+account.getPassword()+"------"+account.getUsername()+"----------"+account.getRoles());
		System.out.println("------------------End------------------");

		if(account != null) {
			System.out.println("------------------Account not null------------------");
			return new UserDetails()  {

				@Override
				public boolean isEnabled() {
					System.out.println("------------------Enable is true------------------");
					return account.getEnable();
				}

				@Override
				public boolean isCredentialsNonExpired() {
					System.out.println("------------------CredentialsNonExpired is true------------------");
					return true;
				}

				@Override
				public boolean isAccountNonLocked() {
					System.out.println("------------------AccountNonLocked is true------------------");
					return true;
				}

				@Override
				public boolean isAccountNonExpired() {
					System.out.println("------------------AccountNonExpired is true------------------");
					return true;
				}

				@Override
				public String getUsername() {
					System.out.println("------------------Username is getting------------------");
					return account.getUsername();
				}

				@Override
				public String getPassword() {
					System.out.println("------------------PassWord is getting------------------");
					return account.getPassword();
				}

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
			};

		} else {
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}
