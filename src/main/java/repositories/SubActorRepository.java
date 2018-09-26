
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SubActor;

@Repository
public interface SubActorRepository extends JpaRepository<SubActor, Integer> {

}
