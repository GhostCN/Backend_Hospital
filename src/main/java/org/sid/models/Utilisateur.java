package org.sid.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Utilisateur {
	@Id @GeneratedValue
	private Long id;
	/*@NotEmpty
	@Size(min=2,max=10)*/
	private String nom;
	/*@NotEmpty
	@Size(min=2,max=10)*/
	private String prenom;
/*	@NotEmpty
	@Size(min=2,max=10)*/
	private String tel;
	/*@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	@Past*/
	private Date dateNaiss;
	@Column(unique=true)
	/*@NotEmpty
	@Size(min=2,max=10)*/
	private String matricule;
	private String password;
	@OneToMany(mappedBy="utilisateur",fetch=FetchType.LAZY)
	private List<Consultation>consultations;
	@ManyToOne
	@JoinColumn(name="poste")
	//@NotNull
	
	private Poste poste;
	@ManyToMany(fetch=FetchType.EAGER)
	//@NotEmpty
	private List<Role>roles;
	@ManyToOne
	@JoinColumn(name="service")
	//@NotNull
	private Service service;
	private Boolean enabled;
	//@Email
	//@NotNull
	private String email;
	
	public Utilisateur() {
		super();
	}
	
	public Utilisateur(String nom, String prenom, String tel, Date dateNaiss, String matricule, String password,
		 Boolean enabled, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.dateNaiss = dateNaiss;
		this.matricule = matricule;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
	}

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Poste getPoste() {
		return poste;
	}


	public void setPoste(Poste poste) {
		this.poste = poste;
	}


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}
	

}
