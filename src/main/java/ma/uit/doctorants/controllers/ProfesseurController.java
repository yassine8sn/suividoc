package ma.uit.doctorants.controllers;


import java.security.Principal;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import ma.uit.doctorants.models.ActeurCreateForm;
import ma.uit.doctorants.models.EtatAvancement;
import ma.uit.doctorants.models.Papier;
import ma.uit.doctorants.models.RapportActivite;
import ma.uit.doctorants.repositories.ActeurRepository;
import ma.uit.doctorants.repositories.AvancementRepository;
import ma.uit.doctorants.repositories.PapierRepository;
import ma.uit.doctorants.services.ActeurService;
import ma.uit.doctorants.services.DoctorantService;
import ma.uit.doctorants.services.ProfesseurService;
import ma.uit.doctorants.services.UploadFile.UploadService;
import ma.uit.doctorants.services.graphe.reponseGrapheService;
import ma.uit.doctorants.validator.ActeurCreateFormValidator;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
	@Autowired 
	reponseGrapheService rg;
	@Autowired
	ActeurRepository acteur;
	@Autowired
	AvancementRepository avn;
	@Autowired
	UploadService us;
	@Autowired
	PapierRepository pr;
	@Autowired
	DoctorantService ds;
	@Autowired
	ProfesseurService ps;
	@Autowired
	ActeurService as;
	
	private final ActeurCreateFormValidator acteurCreateFormValidator;
	
	
	public ProfesseurController(ActeurCreateFormValidator acteurCreateFormValidator) {
		this.acteurCreateFormValidator = acteurCreateFormValidator;
	}

	@InitBinder("addDoc")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(acteurCreateFormValidator);
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")	
	 @RequestMapping(value = "/Encadrant/add/doctorant", method = RequestMethod.GET)
	 public ModelAndView addDocGet(Model model, Principal principal){
		 model.addAttribute("isUpdate", false);
		 return new ModelAndView("profadddoc", "addDoc", new ActeurCreateForm());
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/add/doctorant", method = RequestMethod.POST)
	 public String addDocPost(@Valid @ModelAttribute("addDoc") ActeurCreateForm doctorant, BindingResult bindingResult,Model model, Principal principal){
		 if (bindingResult.hasErrors()) {
			 	model.addAttribute("isUpdate", false);
	            return "profadddoc";
	        }
		 try {
				System.out.println( acteur.save(ps.create(doctorant,acteur.findByLogin(principal.getName()).getCin())));
	        } catch (DataIntegrityViolationException e) {
	        	model.addAttribute("isUpdate", false);
	            bindingResult.reject("email.exists", "Email already exists");
	            return "profadddoc";
	        }
	
		 return "redirect:/professeur/Encadrant/Liste/doctorant";
	 }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/Liste/doctorant", method = RequestMethod.GET)
	 public ModelAndView listeDocGet(Model model){
		 model.addAttribute("isUpdate", false);
		 model.addAttribute("allDoc",acteur.findAll());
		 return new ModelAndView("listeDoc", "addDoc", new ActeurCreateForm());
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/EtatAvancement/Liste/Papier", method = RequestMethod.GET)
	 public String listePapierEtatAvancement(Model model , Principal principal){
		 model.addAttribute("allPapier",avn.getListEtatAvancementByEnc(acteur.findByLogin(principal.getName()).getCin()));
		 model.addAttribute("docAddPapier",new Papier());
		 return "ListePapierAVNEnc";
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/RapportActivite/Liste/Papier", method = RequestMethod.GET)
	 public String listePapierRapportActivite(Model model , Principal principal){
		 model.addAttribute("allPapier",avn.getListRapportActiviteByEnc(acteur.findByLogin(principal.getName()).getCin()));
		 model.addAttribute("docAddPapier",new Papier());
		 return "ListePapierRAEnc";
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/valider-{id}/Avancement", method = RequestMethod.GET)
	 public String validerPapier(@PathVariable(value="id")  Integer id,Model model){
		 
		 if(avn.getTypeAVN(id).equals("rapportActivite")){
			 model.addAttribute("ReportA", avn.findById_avn(id));
			 model.addAttribute("urlpapier", avn.getLienByID_avn(id));
			 return "EncValideAvnRA";
		 }
		 else
		 {
			 model.addAttribute("EtatAvn",  avn.findById_avn(id));
			 model.addAttribute("urlpapier", avn.getLienByID_avn(id));
			 return "EncValideAvnEtatAvn";
		 }
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/valide/EtatAvancement", method = RequestMethod.POST)
	 public String valideEtatAVN(@Valid @ModelAttribute("EtatAvn") EtatAvancement etat,BindingResult result,Model model ){
		 if(result.hasErrors()){            
	         return "EncValideAvnEtatAvn";        
	     }  
		 etat.setValide(true);
		 etat.setDate_valide(new Date());
		 System.out.println(avn.save(etat));
		 return "redirect:/professeur/Encadrant/EtatAvancement/Liste/Papier";
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/valide/RapportActivite", method = RequestMethod.POST)
	 public String valideRA(@Valid @ModelAttribute("ReportA") RapportActivite rapport,BindingResult result,Model model){
		 if(result.hasErrors()){            
	         return "EncValideAvnRA";        
	     }  
		 rapport.setValide(true);
		 rapport.setDate_valide(new Date());
		 System.out.println(avn.save(rapport));
		 return "redirect:/professeur/Encadrant/RapportActivite/Liste/Papier";
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/Encadrant/{id}/progressionGraphique", method = RequestMethod.GET)
	 public String progressionGraphique(@PathVariable(value="id") String cin,Model model , Principal principal){
		 model.addAttribute("alldoccharts", rg.getAllDoc(acteur.findByLogin(principal.getName()).getCin()));
		 model.addAttribute("doccharts", rg.getDoc(cin));
		 return "charts";
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value="/Encadrant/add/papier", method = RequestMethod.GET)
		public String addetatavnGet(Model model){
			model.addAttribute("docAddPapier",new Papier());
			return "EncAddPapier";
		}
	@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value="/Encadrant/add/papier", method = RequestMethod.POST)
		public String addetatavnPost(@RequestParam("file") MultipartFile file ,@ModelAttribute("docAddPapier") Papier papier, Model model , Principal principal){
			String lien=us.UploadFile(file);
			System.err.println(lien);
			if(lien.equals("faild")){
				model.addAttribute("error", true);
				model.addAttribute("docAddPapier",new Papier());
				return "EncAddPapier";
			}
			papier.setLien(lien);
			papier.setActeur(acteur.findByCin(acteur.findByLogin(principal.getName()).getCin()));
			System.out.println(pr.save(papier));
			model.addAttribute("docAddPapier",new Papier());
			return "redirect:/professeur/Encadrant/Liste/Papier";
		}
	@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value="/Encadrant/Liste/Papier", method = RequestMethod.GET)
		public String listePapier(Model model , Principal principal){
			model.addAttribute("allPapier", avn.getListAvancementByEnc(acteur.findByLogin(principal.getName()).getCin()));
			model.addAttribute("docAddPapier",new Papier());
			return "ListePapierEnc";
		}
	@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value="/Encadrant/Liste/Papier/{id}", method = RequestMethod.GET)
		public String listePapierType(@PathVariable(value="id")  String id,Model model){
			model.addAttribute("allPapier", avn.getListPapierByType(id));
			model.addAttribute("docAddPapier",new Papier());
			return "ListePapierType";
		}
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/Encadrant/Planning", method = RequestMethod.GET)
    public String agenda() {
        return "Agenda";
    }
	
}

