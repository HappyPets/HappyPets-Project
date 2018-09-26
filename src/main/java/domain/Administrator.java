
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	// Attributes -------------------------------------------------------------

	// Getters and setters ----------------------------------------------------

	// Relationships ----------------------------------------------------------
	private Collection<Cause>	causes;


	@Valid
	@NotNull
	@OneToMany
	public Collection<Cause> getCauses() {
		return this.causes;
	}
	public void setCauses(final Collection<Cause> causes) {
		this.causes = causes;
	}
}
