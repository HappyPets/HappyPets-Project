
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

	@Query("select avg(v.advertisements.size) from Vet v")
	Double avgAvertisementsPerVet();

	@Query("select v from Vet v join v.valorationsReceived vr group by v order by sum(vr.stars) desc")
	List<Vet> vetMostValued();
}
