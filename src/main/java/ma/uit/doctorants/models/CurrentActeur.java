package ma.uit.doctorants.models;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentActeur extends org.springframework.security.core.userdetails.User {

	private Acteur acteur;
	
	public CurrentActeur(Acteur acteur){
		super(acteur.getLogin(), acteur.getPassword(), AuthorityUtils.createAuthorityList(acteur.getRole().toString()));
		this.acteur = acteur;
	}

	public Acteur getActeur() {
		return acteur;
	}

	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}

	@Override
	public String toString() {
		return "CurrentActeur [acteur=" + acteur + "]"+ super.toString();
	}
	
	public Role getRole() {
		return acteur.getRole();
	}

	public String getCin() {
		return acteur.getCin();
	}
	
	
}
