package ma.uit.doctorants.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.uit.doctorants.models.Doctorant;
import ma.uit.doctorants.models.Encadrant;
import ma.uit.doctorants.models.EtatAvancement;
import ma.uit.doctorants.models.Papier;
import ma.uit.doctorants.models.RapportActivite;
import ma.uit.doctorants.repositories.ActeurRepository;
import ma.uit.doctorants.repositories.AvancementRepository;
import ma.uit.doctorants.repositories.PapierRepository;

@Service
public class DoctorantService {
	@Autowired
	ActeurRepository ac;
	@Autowired
	AvancementRepository avn;
	@Autowired
	PapierRepository pr;
	
	@Transactional
	public void addObjectPapierInEtatAvancement(Papier papier,String cin_doctorant){
		papier.setActeur(ac.findByCin(cin_doctorant));
		Papier p=pr.save(papier);
		EtatAvancement eAvn=new EtatAvancement();
		eAvn.setId_papier(p);
		eAvn.setDoctorant((Doctorant) ac.findByCin(cin_doctorant));
		eAvn.setEncadrant((Encadrant) ac.findByCin(ac.findByCinEncadrant(cin_doctorant)));
		eAvn.setDate(new Date());
		eAvn.setValide(false);
		avn.save(eAvn);
		
	}
	@Transactional
	public void addObjectPapierInRapportA(Papier papier,String cin_doctorant){
		papier.setActeur(ac.findByCin(cin_doctorant));
		Papier p=pr.save(papier);
		RapportActivite eAvn=new RapportActivite();
		eAvn.setId_papier(p);
		eAvn.setDoctorant((Doctorant) ac.findByCin(cin_doctorant));
		eAvn.setEncadrant((Encadrant) ac.findByCin(ac.findByCinEncadrant(cin_doctorant)));
		eAvn.setDate(new Date());
		eAvn.setValide(false);
		avn.save(eAvn);
		
	}
	
	
}
