package ma.uit.doctorants.controllers;

import ma.uit.doctorants.models.Acteur;
import ma.uit.doctorants.services.MailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MailController {

    org.slf4j.Logger logger = LoggerFactory.getLogger(MailController.class);
    @Autowired
    private MailService mailService;

    @RequestMapping("/SendMail")
    public String sendMail() {
        Acteur acteur = new Acteur();
        acteur.setEmail("yassinusx@gmail.com");

        return "Agenda";
    }
}
