package bj.edem.softcare.repositories;

import bj.edem.softcare.entities.Authorities;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LAWSON
 */
@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Authorities findAuthoritiesByusername(String username);

    List<Authorities> findByAuthorityType(String authorityType);
}
