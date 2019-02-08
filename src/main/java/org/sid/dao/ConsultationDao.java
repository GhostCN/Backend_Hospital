package org.sid.dao;

import org.sid.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationDao extends JpaRepository<Consultation, Long>{

}
