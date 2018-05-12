package ma.uit.doctorants.services;

import ma.uit.doctorants.models.RDV;
import ma.uit.doctorants.repositories.RDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Traveler on 03/02/2017.
 */
@Service
public class RDVSercice {
    @Autowired
    private RDVRepository rdvRepository;


    public RDV addRDV(RDV rdv) {
        return rdvRepository.save(rdv);
    }

    public List<RDV> findAll() {
        return rdvRepository.findAll();
    }
}
