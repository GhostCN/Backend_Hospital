/*package org.sid.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Service;
import org.sid.models.Utilisateur;
import org.sid.service.Account;
import org.sid.web.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private Account account;
	public boolean statut = false;
	

	
	@GetMapping("/addUser")
	public String registerForm(Model model) {

		model.addAttribute("utilisateur", new Utilisateur());
		List<Poste> lpostes = account.getAllPost();
		List<Service> lservices = account.getAllService();
		List<Role> lroles = account.getAllRole();
		model.addAttribute("postes", lpostes);
		model.addAttribute("services", lservices);
		model.addAttribute("Roles", lroles);

		return "ajoutUser";

	}

	@PostMapping("/addUser")
	public String checkForm(@Valid Utilisateur user, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			List<Poste> lpostes = account.getAllPost();
			List<Service> lservices = account.getAllService();
			List<Role> lroles = account.getAllRole();
			model.addAttribute("postes", lpostes);
			model.addAttribute("services", lservices);
			model.addAttribute("Roles", lroles);

			return "ajoutUser";
		}
		user.setPassword("passer");
		user.setEnabled(true);
		account.saveUser(user);
		model.addAttribute("succes", "succes");
		return "ajoutUser";

	}

	@GetMapping("/updateMp")
	public String ChangePasswd(Model model) {

		model.addAttribute("registerForm", new RegisterForm());
		return "ChangePassword";
	}

	@PostMapping("/updateMp")
	public String PasswdChange(@Valid RegisterForm reg, BindingResult bindingresult, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (bindingresult.hasErrors()) {

			return "ChangePassword";
		}
		if (!account.checkpassword(auth.getName(), reg.getAncienMp())) {
			model.addAttribute("erreur", "erreur");
			return "ChangePassword";
		}

		if (!reg.getNouvMp().equals(reg.getConfirMp())) {
			model.addAttribute("erreurConf", "erreur");
			return "ChangePassword";
		}
		account.UpdatePassword(reg.getNouvMp(), auth.getName());
		model.addAttribute("success", "success");
		return "ChangePassword";

	}

	@GetMapping("/listUser")
	public String listeUtilisateur(Model model) {

		List<Utilisateur> listeU = account.allUser();
		model.addAttribute("listes", listeU);
		return "listeUser";

	}

	@PostMapping("/listUser")
	public String alllUtilisateur(Model model) {
		return null;

	}
	
	String matricule="";
	
	@GetMapping("/addRole/{mat}")
	public String ajoutRole(Model model, @PathVariable String mat) {
		matricule=mat;
		Utilisateur u= account.findByMatricule(mat);
	 model.addAttribute("u",u );
	 List<Role> lroles = new ArrayList<Role>();

	 for (Role role : account.getAllRole()) {
		for (Role role2 : u.getRoles()) {
			if(role.getLibelle()!=role2.getLibelle())
			{
				if(!lroles.contains(role)) {
					lroles.add(role);
				}
			}
		}
		lroles.removeAll(u.getRoles());
	}
	 model.addAttribute("Roles", lroles);
		return "ajoutRole";
	}

	@PostMapping("/addRole")
	public String addRole(Utilisateur u,Model model) {
		for(Role role:u.getRoles()) {
			account.addRoleToUser(matricule, role.getLibelle());
		}
		model.addAttribute("succes", "succes");
		return "ajoutRole";

	}

	@GetMapping("/rmRole/{mat}")
	public String removRole(Model model,@PathVariable String mat) {
		matricule=mat;
		Utilisateur u= account.findByMatricule(mat);
		 model.addAttribute("u",u );
		 List<Role> lroles = u.getRoles();
		 model.addAttribute("Roles", lroles);
		
		return "removeRole";

	}

	@PostMapping("/rmRole")
	public String removeRole(Utilisateur u,Model model) {
		System.out.println("Matricule "+matricule);
		System.out.println("role "+u.getRoles());
		for(Role role:u.getRoles()) {
			account.rmRoleToUser(matricule, role.getLibelle());
		}
		model.addAttribute("succes", "succes");
		return "removeRole";
	}
	@GetMapping("/statut/{mat}")
	public String activDesactiv(Model model,@PathVariable String mat)
	{
		List<Utilisateur> listeU = account.allUser();
		model.addAttribute("listes", listeU);
		account.activDesactiv(mat);
		model.addAttribute("succes", "succes");
		return "menuAdmin";
		
	}
	

}*/
