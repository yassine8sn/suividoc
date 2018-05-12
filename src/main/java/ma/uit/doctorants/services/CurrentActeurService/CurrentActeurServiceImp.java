package ma.uit.doctorants.services.CurrentActeurService;

import org.springframework.stereotype.Service;

import ma.uit.doctorants.models.CurrentActeur;
import ma.uit.doctorants.models.Role;
@Service
public class CurrentActeurServiceImp  implements CurrentActeurService{

	@Override
	public boolean canAccessUser(CurrentActeur currentActeur, String cin) {
		return currentActeur != null
                && (currentActeur.getRole() == Role.ADMIN || currentActeur.getCin().equals(cin));
	}

}
