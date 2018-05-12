package ma.uit.doctorants.models;

import javax.persistence.*;
import javax.print.Doc;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Traveler on 03/02/2017.
 */
@Entity
public class RDV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date debut;
    private Date fin;

    @OneToOne
    private Encadrant encadrant;


    @OneToMany
    private List<Doctorant> doctorants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public List<Doctorant> getDoctorants() {
        return doctorants;
    }

    public void setDoctorants(List<Doctorant> doctorants) {
        this.doctorants = doctorants;
    }
}
