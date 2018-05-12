package ma.uit.doctorants.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Traveler on 25/01/2017.
 */
@Entity
public class Papier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String lien;
    private String Type;
   

	@ManyToOne
    @JoinColumn
    private Acteur acteur;
	
    public Acteur getActeur() {
        return acteur;
    }
    public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}
	
    public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
	public String toString() {
		return "Papier [id=" + id + ", titre=" + titre + ", description=" + description + ", lien=" + lien + ", Type="
				+ Type + ", acteur=" + acteur + "]";
	}
}
