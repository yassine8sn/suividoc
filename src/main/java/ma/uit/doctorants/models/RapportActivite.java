package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "rapportActivite")
public class RapportActivite extends Avancement {
	protected Integer Annee;

	public Integer getAnnee() {
		return Annee;
	}

	public void setAnnee(Integer annee) {
		Annee = annee;
	}
	
}
