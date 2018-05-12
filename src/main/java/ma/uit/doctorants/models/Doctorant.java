package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Traveler on 25/01/2017.
 */
@Entity
@DiscriminatorValue(value = "doctorant")
public class Doctorant extends Acteur {
    private String CIN_encadrant;
    @OneToMany(mappedBy = "doctorant")
    private List<Avancement> avancements;
    

    public String getCIN_encadrant() {
		return CIN_encadrant;
	}

	public void setCIN_encadrant(String cIN_encadrant) {
		CIN_encadrant = cIN_encadrant;
	}

	public List<Avancement> getAvancements() {
        return avancements;
    }

    public void setAvancements(List<Avancement> avancements) {
        this.avancements = avancements;
    }
}
