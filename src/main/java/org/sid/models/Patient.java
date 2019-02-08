package org.sid.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Patient {
	@Id @GeneratedValue
	private Long id;
	private String numerodossier;
	private String nom;
	private String tel;
	@Temporal(TemporalType.DATE)
	private Date datenaiss;
	@OneToMany(mappedBy="patient",fetch=FetchType.LAZY)
	private List<Consultation>consultations;
	public Patient(String numerodossier, String nom, String tel, Date datenaiss) {
		super();
		this.numerodossier = numerodossier;
		this.nom = nom;
		this.tel = tel;
		this.datenaiss = datenaiss;
	}
	public Patient() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumerodossier() {
		return numerodossier;
	}
	public void setNumerodossier(String numerodossier) {
		this.numerodossier = numerodossier;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getDatenaiss() {
		return datenaiss;
	}
	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}
	

}
