package org.sid.dao;

import org.sid.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Long>{

}
