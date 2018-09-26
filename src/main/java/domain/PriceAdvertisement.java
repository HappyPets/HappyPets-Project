
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class PriceAdvertisement extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private double	value;


	// Getters and setters ----------------------------------------------------
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public double getValue() {
		return this.value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	// Relationships ----------------------------------------------------------
}
