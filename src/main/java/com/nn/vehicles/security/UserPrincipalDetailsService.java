package com.nn.vehicles.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nn.vehicles.model.User;
import com.nn.vehicles.repository.UserRepository;
import com.nn.vehicles.security.UserPrincipal;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	 private UserRepository userRepository;
	 public UserPrincipalDetailsService(UserRepository userRepository) {
	     this.userRepository = userRepository;
	 }
	    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);

		return userPrincipal;
	}

}
