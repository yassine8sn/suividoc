package ma.uit.doctorants.controllers;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ma.uit.doctorants.models.ActeurCreateForm;
import ma.uit.doctorants.models.Papier;
import ma.uit.doctorants.repositories.ActeurRepository;
import ma.uit.doctorants.repositories.AvancementRepository;
import ma.uit.doctorants.repositories.PapierRepository;
import ma.uit.doctorants.services.ActeurService;
import ma.uit.doctorants.services.DoctorantService;
import ma.uit.doctorants.services.ProfesseurService;
import ma.uit.doctorants.services.UploadFile.UploadService;
import ma.uit.doctorants.validator.ActeurCreateFormValidator;

@Controller
@RequestMapping("/doctorant")
public class DoctorantController {
	@Autowired
	UploadService us;
	@Autowired
	PapierRepository pr;
	@Autowired
	DoctorantService ds;
	@Autowired
	AvancementRepository avn;
	@Autowired
	ActeurRepository acteur;
	@Autowired
	ActeurService as;
	@Autowired
	ProfesseurService ps;
	
	
	
	@RequestMapping(value="/add/papier", method = RequestMethod.GET)
	public String addetatavnGet(Model model, Principal principal ){
		model.addAttribute("docAddPapier",new Papier());
		return "docAddPapier";
	}
	@RequestMapping(value="/add/papier", method = RequestMethod.POST)
	public String addetatavnPost(@RequestParam("file") MultipartFile file ,@ModelAttribute("docAddPapier") Papier papier, Model model, Principal principal){
		String lien=us.UploadFile(file);
		System.err.println(lien);
		if(lien.equals("faild")){
			model.addAttribute("error", true);
			model.addAttribute("docAddPapier",new Papier());
			return "docAddPapier";
		}
		papier.setLien(lien);
		switch(papier.getType())
		{
		case "Etat d'avancement":
								ds.addObjectPapierInEtatAvancement(papier, acteur.findByLogin(principal.getName()).getCin());
								break;
		case "Rapport d'Activité":
								ds.addObjectPapierInRapportA(papier, acteur.findByLogin(principal.getName()).getCin());
								break;
	    default 				:
	    						papier.setActeur(acteur.findByLogin(principal.getName()));
	    						System.out.println(pr.save(papier));
	    						break;
		}
		
		model.addAttribute("docAddPapier",new Papier());
		return "redirect:/doctorant/Liste/Papier";
	}
	
	@PreAuthorize("hasAuthority('USER')")
	 @RequestMapping(value = "/update-{id}", method = RequestMethod.GET)
	 public String updateDocGet(@PathVariable(value="id")  String id ,Model model){
		 model.addAttribute("addDoc",as.toActeurCreateForm(acteur.findByCin(id)));
		 model.addAttribute("isUpdate",true);
		 return "updatedoc";
	 }
	@PreAuthorize("hasAuthority('USER')")
	 @RequestMapping(value = "/update", method = RequestMethod.GET)
	 public String updateDocGet1(){
		 return "home";
	 }
	@PreAuthorize("hasAuthority('USER')")
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	 public String updateDocPost(@Valid @ModelAttribute("addDoc") ActeurCreateForm doctorant,BindingResult bindingResult,Model model, Principal principal){
		if (bindingResult.hasErrors()) {
		 	model.addAttribute("isUpdate", false);
            return "profadddoc";
        }
	 try {
			System.out.println( acteur.save(ps.create(doctorant,acteur.findByCinEncadrant(acteur.findByLogin(principal.getName()).getCin()))));
        } catch (DataIntegrityViolationException e) {
        	model.addAttribute("isUpdate", false);
            bindingResult.reject("email.exists", "");
            return "profadddoc";
        }
		 model.addAttribute("isUpdate",true);
		 return "home";
	 }
	@RequestMapping(value="/Liste/Papier", method = RequestMethod.GET)
	public String listePapier(Model model, Principal principal){
		model.addAttribute("allPapier", avn.getListAvancementByDoc(acteur.findByLogin(principal.getName()).getCin()));
		model.addAttribute("docAddPapier",new Papier());
		return "ListePapierDoc";
	}
	@RequestMapping(value="/Liste/Papier/{id}", method = RequestMethod.GET)
	public String listePapierType(@PathVariable(value="id")  String id,Model model){
		model.addAttribute("allPapier", avn.getListPapierByType(id));
		model.addAttribute("docAddPapier",new Papier());
		return "ListePapierType";
	}
}
