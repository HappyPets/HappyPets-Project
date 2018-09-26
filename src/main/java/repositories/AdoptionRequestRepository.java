
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AdoptionRequest;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Integer> {

	@Query("select count(ar) from AdoptionRequest ar where ar.status= 'ACCEPTED'")
	Integer nAdoptionRequestAccepted();

	@Query("select count(ar) from AdoptionRequest ar where ar.status= 'DENIED'")
	Integer nAdoptionRequestDenied();

	@Query("select ((select count(ar) from AdoptionRequest ar where ar.status= 'ACCEPTED') / count(ar1))*100.0 from AdoptionRequest ar1 where ar1.status= 'DENIED'")
	Double ratioAdoptionRequestAcceptedVSDenied();
}
