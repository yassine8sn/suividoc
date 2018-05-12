package ma.uit.doctorants.services;

import java.util.Optional;

import ma.uit.doctorants.models.Acteur;
import ma.uit.doctorants.models.ActeurCreateForm;


public interface ActeurService {
	Optional<Acteur> getUserByCin(String cin);
	Optional<Acteur> getUserByLogin(String login);
	Optional<Acteur> getUserByEmail(String email);
	ActeurCreateForm toActeurCreateForm(Acteur acteur);
}
