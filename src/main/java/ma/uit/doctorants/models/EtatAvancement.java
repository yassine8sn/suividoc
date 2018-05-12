package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "etatAvancement")
public class EtatAvancement extends Avancement {
	protected Integer mois;

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}
	
}
