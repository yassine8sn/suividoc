package ma.uit.doctorants.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.uit.doctorants.models.Avancement;

public interface AvancementRepository  extends JpaRepository<Avancement, String> {

	@Query(nativeQuery = true, value ="SELECT a1.valide, a.nom,a.prenom ,p.lien,a1.note,a1.mois,a1.annee ,p.type,a.cin "
			+ "FROM acteur a,papier p,avancement a1 "
			+ "WHERE a.cin=a1.doctorant_cin and a1.id=p.id and a1.doctorant_cin= :cin") 
	List<Object[]> getListAvancementByDoc(@Param("cin") String cin);
	@Query(nativeQuery = true, value ="SELECT a1.valide, a.nom,a.prenom ,p.lien,p.titre,p.type,p.description,a1.id_avn,a.cin "
			+ "FROM acteur a,papier p,avancement a1 "
			+ "WHERE a.cin=a1.doctorant_cin and a1.id=p.id and a1.encadrant_cin= :cin") 
	List<Object[]> getListAvancementByEnc(@Param("cin") String cin);
	
	@Query(nativeQuery = true, value ="SELECT type FROM avancement WHERE id_avn= :id")
	String getTypeAVN(@Param("id") Integer id);

	@Query(nativeQuery = true, value ="SELECT p.lien FROM papier p , avancement a WHERE p.id=a.id and a.id_avn= :id")
	String getLienByID_avn(@Param("id") Integer id);
	@Query(nativeQuery = true, value ="SELECT * FROM avancement a WHERE a.id_avn= :id")
	Avancement findById_avn(@Param("id") Integer id_avn);
	@Query(nativeQuery = true, value ="SELECT a.mois,a.note FROM avancement a WHERE a.doctorant_cin= :cin and a.type='etatAvancement' ORDER BY a.mois ASC")
	List<Object[]> getMoisNoteDe(@Param("cin") String cin);
	@Query(nativeQuery = true, value ="SELECT a1.valide, a.nom,a.prenom ,p.lien,p.titre,p.type,p.description,a1.id_avn ,a.cin "
			+ "FROM acteur a,papier p,avancement a1 "
			+ "WHERE a.cin=a1.doctorant_cin and a1.id=p.id and a1.type='etatAvancement' and a1.encadrant_cin= :cin")
	List<Object[]> getListEtatAvancementByEnc(@Param("cin") String cin);
	@Query(nativeQuery = true, value ="SELECT a1.valide, a.nom,a.prenom ,p.lien,p.titre,p.type,p.description,a1.id_avn ,a1.annee "
			+ "FROM acteur a,papier p,avancement a1 "
			+ "WHERE a.cin=a1.doctorant_cin and a1.id=p.id and a1.type='rapportActivite' and a1.encadrant_cin= :cin ")
	List<Object[]> getListRapportActiviteByEnc(@Param("cin") String cin);
	@Query(nativeQuery = true, value ="SELECT  a.nom,a.prenom ,p.lien,p.titre,p.type,p.description ,a.cin "
			+ "FROM acteur a,papier p "
			+ "WHERE p.type= :type and a.cin=p.acteur_cin")
	List<Object[]> getListPapierByType(@Param("type") String type );
}
