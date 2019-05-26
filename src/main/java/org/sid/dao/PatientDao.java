package org.sid.dao;

import org.sid.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("*")
public interface PatientDao extends JpaRepository<Patient, Long>{
public Patient findByNumerodossier(String num);
}
