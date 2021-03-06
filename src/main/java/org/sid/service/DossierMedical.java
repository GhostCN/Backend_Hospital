package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.models.Consultation;
import org.sid.models.Patient;

public interface DossierMedical {
	
	public int addConsultation(Consultation c);
	public Consultation updateConsultation(Long id,Consultation c);
	public  Optional<Consultation> getConsultation(Long id);
	public Patient findByNumerodossier(String num);
	public Patient addPatient(Patient p);
	public List<Patient>listPatient();
}
