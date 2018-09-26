
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cause;

@Repository
public interface CauseRepository extends JpaRepository<Cause, Integer> {

	@Query("select c from Cause c where c.donations.size= (select max(c1.donations.size) from Cause c1)")
	Collection<Cause> causeWithMoreDonations();
}
