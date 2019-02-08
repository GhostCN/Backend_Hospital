package org.sid.service;



import java.util.List;

import org.sid.dao.ConsultationDao;
import org.sid.dao.PatientDao;
import org.sid.models.Consultation;
import org.sid.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class DossMedicImpl implements DossierMedical{
	@Autowired
  public ConsultationDao medic;
	@Autowired
	private PatientDao pdao;
	@Override
	public int addConsultation(Consultation c) {
		int x=0;
		try {
			medic.save(c);
			x=1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return x;
	}

	@Override
	public Consultation updateConsultation(Long id,Consultation c) {
		c.setId(id);
		return medic.save(c);
	}

	@Override
	public Patient addPatient(Patient p) {
		// TODO Auto-generated method stub
		return pdao.save(p);
	}

	@Override
	public List<Patient> listPatient() {
	
		return pdao.findAll();
	}

}
