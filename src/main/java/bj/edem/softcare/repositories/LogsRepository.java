package bj.edem.softcare.repositories;

import bj.edem.softcare.entities.Logs;
import bj.edem.softcare.entities.Users;
//import bj.edem.bbs.entities.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LAWSON
 */
@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

    List<Logs> findByUser(Users user);
}
