package org.sid.dao;

import org.sid.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long>{

}
