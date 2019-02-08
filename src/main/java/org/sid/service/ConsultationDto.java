package org.sid.service;

import java.util.Date;
import java.util.List;

import org.sid.models.Patient;
import org.sid.models.Service;
import org.sid.models.Utilisateur;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationDto {
	private Long id;
	private Date date;
	private String commentaire;
	private String prescription;
	private Utilisateur utilisateur;
	private Patient patients;
	private List<Utilisateur> utilisateurs;
	private Service services;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Patient getPatients() {
		return patients;
	}

	public void setPatients(Patient patients) {
		this.patients = patients;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}



	public Service getServices() {
		return services;
	}

	public void setServices(Service services) {
		this.services = services;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@JsonProperty("service")
	private void convertedService(Integer services) {
		Service s = new Service();
		s.setId(services.longValue());
		this.services=s;
		
	}

	@JsonProperty("patient")
	private void convertedPatient(Integer patients) {
		Patient p = new Patient();
		p.setId(patients.longValue());
		this.patients = p;

	}

}
