package ma.uit.doctorants.services;

import ma.uit.doctorants.models.Acteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Traveler on 03/02/2017.
 */
@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String to, Date debut, Date fin) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("encadrant1@gmail.com");
        message.setSubject("RDV");
        message.setText("Salam Monsieur,\n\nRDV de " + debut + " à " + fin + "\n\nCordialement");

        javaMailSender.send(message);
        System.out.println(message);
    }
}
