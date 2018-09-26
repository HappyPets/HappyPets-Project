
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class TabooWords extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Collection<String>	words;


	@NotNull
	@ElementCollection
	public Collection<String> getWords() {
		return this.words;
	}

	public void setWords(final Collection<String> words) {
		this.words = words;
	}

	// Getters and setters ----------------------------------------------------

}
