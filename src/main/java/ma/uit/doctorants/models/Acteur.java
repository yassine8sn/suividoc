package ma.uit.doctorants.models;

import javax.persistence.*;

import org.hibernate.annotations.DiscriminatorOptions;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force=true)
public class Acteur implements Serializable {

	@Id
	protected String cin;
	protected String nom, prenom, email, login, sexe, password;
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	@Transient
	protected List<Papier> papiers;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCin() {
		return cin;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setCin(String cin) {
		this.cin = cin;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Papier> getPapiers() {
		return papiers;
	}

	public void setPapiers(List<Papier> papiers) {
		this.papiers = papiers;
	}

	@Override
	public String toString() {
		return "Acteur{" + "cin='" + cin + '\'' + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + ", email='"
				+ email + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + '}';
	}
}
