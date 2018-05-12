package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Traveler on 26/01/2017.
 */
@Entity
@DiscriminatorValue(value = "encadrant")
public class Encadrant extends Professeur {

    @OneToMany(mappedBy = "encadrant")
    private List<Avancement> avancements;


    public List<Avancement> getAvancements() {
        return avancements;
    }

    public void setAvancements(List<Avancement> avancements) {
        this.avancements = avancements;
    }
}
