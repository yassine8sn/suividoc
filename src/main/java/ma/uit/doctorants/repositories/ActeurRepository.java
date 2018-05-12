package ma.uit.doctorants.repositories;

import ma.uit.doctorants.models.Acteur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ActeurRepository extends JpaRepository<Acteur, String> {
	Acteur findByCin(String cin);
	Optional<Acteur> findOneByCin(String cin);
	Acteur findByEmail(String email);
	Optional<Acteur> findOneByEmail(String email);
	Acteur findByLogin(String login);
	Optional<Acteur> findOneByLogin(String login);
	@Query(nativeQuery = true, value ="SELECT a.cin_encadrant FROM acteur a WHERE a.cin= :cin ")
	String findByCinEncadrant(@Param("cin") String cin);
	@Query(nativeQuery = true, value ="SELECT a.cin FROM acteur a WHERE cin_encadrant= :cin")
	List<String> getListDocBy(@Param("cin") String enc);
}
