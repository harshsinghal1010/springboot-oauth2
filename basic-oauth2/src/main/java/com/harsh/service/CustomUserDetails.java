package com.harsh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harsh.dao.UserRepository;
import com.harsh.entity.Role;
import com.harsh.entity.User;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	
    	Optional<User> user = repo.findByUsername(username);
    	if(user==null)
    		return null;
    	else {
    	
    		List<GrantedAuthority> list = new ArrayList();
    		for(Role role : user.get().getRoles())
    				list.add(new SimpleGrantedAuthority(role.getName()));
    				
    		return new org.springframework.security.core.userdetails
    				.User(user.get().getUsername(),
    						user.get().getPassword(), 
    						list);
    	}
    	
    	
       
    }
}