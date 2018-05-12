package ma.uit.doctorants.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "chefLaboratoire")
public class ChefLaboratoire extends Professeur {
}
