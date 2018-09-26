
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Valoration extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Integer	stars;


	// Getters and setters ----------------------------------------------------
	@NotNull
	@Range(min = 0, max = 5)
	public Integer getStars() {
		return this.stars;
	}

	public void setStars(final Integer stars) {
		this.stars = stars;
	}


	// Relationships ----------------------------------------------------------
	private User		valorator;
	private SubActor	valorated;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getValorator() {
		return this.valorator;
	}

	public void setValorator(final User valorator) {
		this.valorator = valorator;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public SubActor getValorated() {
		return this.valorated;
	}

	public void setValorated(final SubActor valorated) {
		this.valorated = valorated;
	}

}
