package org.sid.web;

import java.util.List;

import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Service;
import org.sid.models.Utilisateur;
import org.sid.service.Account;
import org.sid.service.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AccountRestController {
	@Autowired
	private Account account;
	@PostMapping("/register")
	public Utilisateur register(@RequestBody RegisterForm reg)
	{
	
		Utilisateur uti=account.findByMatricule(reg.getMatricule());
		if(uti!=null) {
			throw new RuntimeException("this user already exist");
		}
		else {
			Utilisateur u=new Utilisateur();
		
			u.setMatricule(reg.getMatricule());
			u.setNom(reg.getNom());
			u.setPrenom(reg.getPrenom());
			u.setEmail(reg.getEmail());
			u.setDateNaiss(reg.getDate());
			u.setPassword("passer");
			u.setTel(reg.getTelephone());
			u.setPoste(reg.getPostes().get(0));
			u.setService(reg.getServices().get(0));
			u.setEnabled(true);
			u.setRoles(reg.getRole());
			return account.saveUser(u);	
		}
		
	}
	@PostMapping("/update")
	public String PasswdChange(@RequestBody RegisterForm reg) {
		System.out.println("-------------update------");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
		if (!account.checkpassword(auth.getName(), reg.getAncienMp())) {
			throw new RuntimeException("Mot de passe incorrect");
		}
System.out.println("Mot de passe"+auth.getName());
		if (!reg.getNouvMp().equals(reg.getConfirMp())) {
			throw new RuntimeException("You should confirm your password");
		}
		 account.UpdatePassword(reg.getNouvMp(), auth.getName());
		 return "good";
	}
	
	@GetMapping("/lposte")
	public List<Poste>loadPoste()
	{
		List<Poste> lpostes = account.getAllPost();
		return lpostes;
		
	}
	@GetMapping("/lservice")
	public List<Service>loadService()
	{
		List<Service> lservices = account.getAllService();
		return lservices;
		
	}
	
	@GetMapping("/lrole")
	public List<Role>loadRole()
	{
		List<Role> lroles = account.getAllRole();
		return lroles;
		
	}
}
