package ma.uit.doctorants.services.CurrentActeurService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.uit.doctorants.models.Acteur;
import ma.uit.doctorants.models.CurrentActeur;
import ma.uit.doctorants.repositories.ActeurRepository;
@Service
public class CurrentActeurDetailsService implements UserDetailsService {
	@Autowired
	ActeurRepository acteur;
	
	public CurrentActeurDetailsService(ActeurRepository acteur) {
		this.acteur = acteur;
	}

	@Override
	public CurrentActeur loadUserByUsername(String login) throws UsernameNotFoundException {
		Acteur ac=acteur.findOneByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User with login=%s was not found", login)));
				return new CurrentActeur(ac);
	}

}
