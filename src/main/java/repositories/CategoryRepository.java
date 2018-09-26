
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select ar.pet.category from AdoptionRequest ar where ar.status= 'ACCEPTED' group by ar.pet.category order by count(ar) desc")
	Collection<Category> categoriesOrderByNumAdoptionRequestAccepted();
}
