
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.TabooWords;

@Repository
public interface TabooWordsRepository extends JpaRepository<TabooWords, Integer> {

}
