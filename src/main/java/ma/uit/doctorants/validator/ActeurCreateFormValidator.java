package ma.uit.doctorants.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ma.uit.doctorants.models.ActeurCreateForm;
import ma.uit.doctorants.services.ActeurService;

@Component
public class ActeurCreateFormValidator implements Validator{
	private final  ActeurService acteurService;
	@Autowired
	public ActeurCreateFormValidator(ActeurService acteur) {
		this.acteurService = acteur;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ActeurCreateForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ActeurCreateForm acf=(ActeurCreateForm) target;
		System.err.println(acf.getUpdate());
		if(acf.getUpdate()){
        validateLogin(errors, acf);
        validateEmail(errors, acf);
        validateCin(errors,acf);
        }
		validatePasswords(errors, acf);
	}
	private void validateCin(Errors errors, ActeurCreateForm acf) {
		if(acteurService.getUserByCin(acf.getCin()).isPresent())
			errors.reject("cin.exists", " CIN existe déjà");	
	}
	private void validateEmail(Errors errors, ActeurCreateForm acf) {
		if(acteurService.getUserByEmail(acf.getEmail()).isPresent())
			errors.reject("email.exists", "Email existe déjà");
	}
	private void validateLogin(Errors errors, ActeurCreateForm acf) {
		if(acteurService.getUserByLogin(acf.getLogin()).isPresent())
			errors.reject("login.exists", "Login existe déjà");
		
	}
	private void validatePasswords(Errors errors, ActeurCreateForm acf) {
		if(!acf.getPassword().equals(acf.getRepassword())){
			errors.reject("password.no_match", "Les mots de passe ne correspondent pas");
		}
	}

}
