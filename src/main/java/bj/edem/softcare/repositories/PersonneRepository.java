package bj.edem.softcare.repositories;

import bj.edem.softcare.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LAWSON
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    
    Boolean existsByNomPrenomsAndContact(String nomPrenoms, String contact);
    
    Personne findByNomPrenomsAndContact(String nomPrenoms, String contact);
}
