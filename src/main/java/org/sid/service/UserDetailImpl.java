package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailImpl implements UserDetailsService {
	@Autowired
	private Account account;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur u=account.findByMatricule(username);
		if(u==null) {
			throw new UsernameNotFoundException(username);
		}
		Collection<GrantedAuthority>authorities=new ArrayList<GrantedAuthority>();
		u.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getLibelle()));
		});
		return new User(u.getMatricule(),u.getPassword(),authorities);
	}

}
