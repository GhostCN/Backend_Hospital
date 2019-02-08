package org.sid.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Service {
	@Id @GeneratedValue
	private Long id;
	private String libelle;
	
	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	private List<Consultation>consultations;
	@JsonIgnore
	@OneToMany(mappedBy="service",fetch=FetchType.LAZY)
	private List<Utilisateur>user;
	
	public Service() {
		super();
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Service(String libelle) {
		super();
		this.libelle = libelle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.libelle;
	}


}
