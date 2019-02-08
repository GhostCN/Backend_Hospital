package org.sid.web;

import java.util.List;

import org.sid.models.Consultation;
import org.sid.models.Patient;
import org.sid.models.Utilisateur;
import org.sid.service.Account;
import org.sid.service.ConsultationDto;
import org.sid.service.DossierMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DocMedicRestController {
	@Autowired
	private DossierMedical doc;
	@Autowired
	private Account ac;
	@PostMapping("/addCons")
	public int addConsultation(@RequestBody ConsultationDto cons) 
	{
		System.out.println("---------------------patient-----------------------");
		Consultation consult=new Consultation();
		consult.setDate(cons.getDate());
		consult.setCommentaire(cons.getCommentaire());
		consult.setPrescription(cons.getPrescription());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Utilisateur u=ac.findByMatricule(auth.getName());
		consult.setPatient(cons.getPatients());
		
		consult.setService(cons.getServices());
		consult.setUtilisateur(u);
		int ok=doc.addConsultation(consult);
		if(ok>0) {
			return ok;
		}
		else {
			return ok;
		}	
	}
	
	@GetMapping("/listCons")
	public List<Consultation>listConsult(){
		return ac.getAllConsultation();
	}
	
	@PostMapping("/addPatient")
	public Patient addPatient(@RequestBody Patient p) {
		return doc.addPatient(p);
		
	}
	@GetMapping("/patients")
	public List<Patient>listePatient()
	{
		return doc.listPatient();
	}
}
