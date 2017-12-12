package com.brushmyskills.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brushmyskills.security.model.CustomUserDetails;
import com.brushmyskills.security.model.User;
import com.brushmyskills.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser =userRepository.findByName(username);
		
		optionalUser.orElseThrow(()-> new UsernameNotFoundException("User name not presents"));
		
		
		/*CustomUserDetails customUserDetails = optionalUser.map((user)-> {
			return new CustomUserDetails(user);
		}).get();*/
		
		return optionalUser.map(CustomUserDetails::new).get(); 
	
	}

}
