
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//	@Query("select u from User u where u.stars = (select max(u1.stars) from User u1)")
	//	Collection<User> userMostValued();
}
