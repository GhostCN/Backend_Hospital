/*package org.sid.controller;

import java.util.List;

import javax.validation.Valid;

import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Service;
import org.sid.service.Account;
import org.sid.service.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/medecin")
public class MedecinController {
	@Autowired
	private Account account;
	@GetMapping("/medecin")
	public String medecinForm(Model model) {

		model.addAttribute("RegisterForm",new RegisterForm());
		List<Poste>lpostes=account.getAllPost();
		List<Service>lservices=account.getAllService();
		List<Role>lroles=account.getAllRole();
		model.addAttribute("postes", lpostes);
		model.addAttribute("services", lservices);
		model.addAttribute("roles", lroles);

		return "ajoutUser";
		
	}
	@PostMapping("/medecin")
	public String medecinChech(@Valid RegisterForm register,BindingResult bindingresult)
	{
		if(bindingresult.hasErrors())
		{
			return "ajoutUser";
		}
		return "redirect:/meunuMedecin";
		
	}
	
	


}
*/