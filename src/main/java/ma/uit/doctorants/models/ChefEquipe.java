package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Traveler on 25/01/2017.
 */
@Entity
@DiscriminatorValue(value = "chefEquipe")
public class ChefEquipe extends Professeur {
}
