package ma.uit.doctorants.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.uit.doctorants.models.ActeurCreateForm;
import ma.uit.doctorants.models.Doctorant;
import ma.uit.doctorants.models.Role;
import ma.uit.doctorants.repositories.ActeurRepository;

@Service
public class ProfesseurService {
	@Autowired
	ActeurRepository acteur;
	@Transactional
	 public Doctorant create(ActeurCreateForm form,String cin) {
			Doctorant user = new Doctorant();
	        user.setEmail(form.getEmail());
	        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
	        user.setRole(Role.USER);
	        user.setCin(form.getCin());
	        user.setNom(form.getNom());
	        user.setSexe(form.getSexe());
	        user.setLogin(form.getLogin());
	        user.setPrenom(form.getPrenom());
	        user.setCIN_encadrant(cin);
	        return acteur.save(user);
	    }
}
