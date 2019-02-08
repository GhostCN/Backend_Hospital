package org.sid.dao;

import org.sid.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Utilisateur, Long>{
public boolean findByEnabled(Long id);
public Utilisateur findByMatricule(String matricule);
@Modifying
@Query("update from Utilisateur u set u.password=:password  where matricule=:matricule")
public void UpdateUser(@Param("password") String password,@Param("matricule") String matricule);
@Modifying
@Query("update from Utilisateur u set u.enabled=:enabled  where matricule=:matricule")
public void activDesactiv(@Param("enabled")boolean stat,@Param("matricule")String mat);
}
