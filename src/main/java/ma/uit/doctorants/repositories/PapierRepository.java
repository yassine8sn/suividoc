package ma.uit.doctorants.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.uit.doctorants.models.Papier;

public interface PapierRepository extends JpaRepository<Papier, String> {
}
