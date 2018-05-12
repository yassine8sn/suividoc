package ma.uit.doctorants.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Traveler on 26/01/2017.
 */
@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Avancement implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id_avn;
	
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    protected Date date;
    @Min(0) @Max(10)
    protected int note;
    protected String remarque;
    protected boolean valide;
    protected Date date_valide;
    @ManyToOne
    @JoinColumn
    protected Encadrant encadrant;

    @ManyToOne
    @JoinColumn
    protected Doctorant doctorant;
    @OneToOne
    @JoinColumn(name="id")
    protected Papier id_papier;
    
    
    public Date getDate_valide() {
		return date_valide;
	}

	public void setDate_valide(Date date_valide) {
		this.date_valide = date_valide;
	}

	public Integer getId_avn() {
		return id_avn;
	}

	public void setId_avn(Integer id_avn) {
		this.id_avn = id_avn;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Papier getId_papier() {
		return id_papier;
	}

	public void setId_papier(Papier id_papier) {
		this.id_papier = id_papier;
	}

	public boolean getValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
 
    
	public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Doctorant getDoctorant() {
        return doctorant;
    }

    public void setDoctorant(Doctorant doctorant) {
        this.doctorant = doctorant;
    }

	@Override
	public String toString() {
		return "Avancement [id_avn=" + id_avn + ", date=" + date + ", note=" + note + ", remarque=" + remarque
				+ ", valide=" + valide + ", encadrant=" + encadrant + ", doctorant=" + doctorant + ", id_papier="
				+ id_papier + "]";
	}
    
}
