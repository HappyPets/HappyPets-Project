
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Vet extends SubActor {

	// Attributes -------------------------------------------------------------
	private String	licenseNumber;


	// Getters and setters ----------------------------------------------------

	@SafeHtml
	@NotBlank
	@Pattern(regexp = "(^\\d{10}$)")
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(final String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	// Relationships ----------------------------------------------------------

}
