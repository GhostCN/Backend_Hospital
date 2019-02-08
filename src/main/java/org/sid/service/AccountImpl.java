package org.sid.service;

import java.util.List;

import org.sid.dao.ConsultationDao;
import org.sid.dao.PosteRepository;
import org.sid.dao.RoleRepository;
import org.sid.dao.ServiceRepository;
import org.sid.dao.UserRepository;
import org.sid.models.Consultation;
import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AccountImpl implements Account{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PosteRepository posteRepository;
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private  ConsultationDao consultationDao;
	@Override
	public Utilisateur saveUser(Utilisateur user) {
		String mpass=bCryptPasswordEncoder.encode("passer");
		user.setPassword(mpass);
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String matricule, String libelle) {
		Utilisateur user=userRepository.findByMatricule(matricule);
		Role role=roleRepository.findByLibelle(libelle);
		user.getRoles().add(role);
		
	} 

	@Override
	public Utilisateur findByMatricule(String matricule) {
		
		return userRepository.findByMatricule(matricule);
	}

	@Override
	public List<Poste> getAllPost() {
		
		return posteRepository.findAll();
	}

	@Override
	public List<org.sid.models.Service> getAllService() {
		return serviceRepository.findAll();
	}

	@Override
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public boolean checkpassword(String matricule, String password) {
		Utilisateur u= userRepository.findByMatricule(matricule);
		if(bCryptPasswordEncoder.matches(password, u.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public void UpdatePassword(String password, String matricule) {
		String mpass=bCryptPasswordEncoder.encode(password);
		userRepository.UpdateUser(mpass,matricule);
	}
	
	public String GetMP(String matricule) {
		Utilisateur u= userRepository.findByMatricule(matricule);
		return u.getPassword();
	}

	@Override
	public List<Utilisateur> allUser() {
		
		return userRepository.findAll();
	}

	@Override
	public void activDesactiv(String mat) {
		Utilisateur u=userRepository.findByMatricule(mat);
		if(u.getEnabled()==true)
		{
			userRepository.activDesactiv(false, mat);
		}
		else {
			userRepository.activDesactiv(true, mat);
		}
	
	}

	@Override
	public List<Role> getAllRoleByMat(String mat) {
		
		return null;
	}

	@Override
	public void rmRoleToUser(String matricule, String libelle) {
		Utilisateur user=userRepository.findByMatricule(matricule);
		Role role=roleRepository.findByLibelle(libelle);
		user.getRoles().remove(role);
		
	}

	@Override
	public List<Consultation> getAllConsultation() {
		// TODO Auto-generated method stub
		return consultationDao.findAll();
	}
}
