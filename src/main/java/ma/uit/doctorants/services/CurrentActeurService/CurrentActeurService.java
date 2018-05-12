package ma.uit.doctorants.services.CurrentActeurService;

import ma.uit.doctorants.models.CurrentActeur;

public interface CurrentActeurService {
	boolean canAccessUser(CurrentActeur currentActeur, String cin);
}
