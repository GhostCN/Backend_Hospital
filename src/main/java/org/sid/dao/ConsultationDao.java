package org.sid.dao;



import org.sid.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultationDao extends JpaRepository<Consultation, Long>{

	//Consultation findOne(Long id);
	
}
