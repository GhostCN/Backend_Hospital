package org.sid.dao;

import org.sid.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByLibelle(String libelle);
}
