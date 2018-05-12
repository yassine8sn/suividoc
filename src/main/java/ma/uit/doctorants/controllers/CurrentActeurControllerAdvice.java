package ma.uit.doctorants.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ma.uit.doctorants.models.CurrentActeur;


@ControllerAdvice
public class CurrentActeurControllerAdvice {

	
	@ModelAttribute("currentActeur")
    public CurrentActeur getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (CurrentActeur) authentication.getPrincipal();
    }
}
