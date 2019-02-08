package org.sid.service;

import java.util.List;

import org.sid.models.Consultation;
import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Service;
import org.sid.models.Utilisateur;

public interface Account {
	public Utilisateur saveUser(Utilisateur user);
	public Role saveRole(Role role);
	public void addRoleToUser(String matricule,String libelle);
	public void rmRoleToUser(String matricule,String libelle);
	public Utilisateur findByMatricule(String matricule);
	public List<Utilisateur>allUser();
	public List<Poste>getAllPost();
	public List<Consultation>getAllConsultation();
	public List<Service>getAllService();
	public List<Role>getAllRole();
	public List<Role>getAllRoleByMat(String matricule);
	public boolean checkpassword(String matricule,String password);
	public void UpdatePassword(String password,String matricule);
	public String GetMP(String matricule);
	public void activDesactiv(String mat);
	

	
}
