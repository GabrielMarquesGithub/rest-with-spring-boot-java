package br.com.nikisgabriel.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.nikisgabriel.model.User;
import br.com.nikisgabriel.repositories.UserRespository;

public class UserServices implements UserDetailsService {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private UserRespository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User entity = repository.findByUserName(username);
		if(entity != null) {
			return entity;
		}else {
			throw new UsernameNotFoundException("Username " + username + " Not found!");
		}
	}

	
}
