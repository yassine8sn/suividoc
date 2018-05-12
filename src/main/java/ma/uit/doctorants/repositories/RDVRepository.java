package ma.uit.doctorants.repositories;

import ma.uit.doctorants.models.RDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RDVRepository extends JpaRepository<RDV, Long> {

}
