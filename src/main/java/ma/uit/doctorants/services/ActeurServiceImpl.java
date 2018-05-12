package ma.uit.doctorants.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.uit.doctorants.models.Acteur;
import ma.uit.doctorants.models.ActeurCreateForm;
import ma.uit.doctorants.repositories.ActeurRepository;
@Service
public class ActeurServiceImpl implements ActeurService{
	@Autowired
	ActeurRepository acteur;
	@Override
	public Optional<Acteur> getUserByCin(String cin) {
		return acteur.findOneByCin(cin);
	}

	@Override
	public Optional<Acteur> getUserByLogin(String login) {
		return acteur.findOneByLogin(login);
	}

	@Override
	public Optional<Acteur> getUserByEmail(String email) {
		return acteur.findOneByEmail(email);
	}

	@Override
	public ActeurCreateForm toActeurCreateForm(Acteur acteur) {
		ActeurCreateForm acf=new ActeurCreateForm();
		acf.setCin(acteur.getCin());
		acf.setEmail(acteur.getEmail());
		acf.setLogin(acteur.getLogin());
		acf.setNom(acteur.getNom());
		acf.setPassword(acteur.getPassword());
		acf.setRepassword(null);
		acf.setPrenom(acteur.getPrenom());
		acf.setSexe(acteur.getSexe());
		return acf;
	}

}
