package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Traveler on 26/01/2017.
 */

@Entity
@DiscriminatorValue(value = "professeur")
@DiscriminatorColumn(name = "fonction", discriminatorType = DiscriminatorType.STRING)
public class Professeur extends Acteur {
}
