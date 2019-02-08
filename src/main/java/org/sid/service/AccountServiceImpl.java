/*package org.sid.service;

import org.sid.dao.AppRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public AppUser saveUser(AppUser user) {
		String codePassword=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(codePassword);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return appRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
	
	AppUser user=userRepository.findByUsername(userName);
	AppRole role=appRepository.findByRolename(roleName);
	user.getRoles().add(role);
		
	}

	@Override
	public AppUser findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
*/