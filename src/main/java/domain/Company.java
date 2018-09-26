
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends SubActor {

	// Attributes -------------------------------------------------------------
	private String	businessName;
	private String	VAT;


	// Getters and setters ----------------------------------------------------
	@NotBlank
	@SafeHtml
	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(final String businessName) {
		this.businessName = businessName;
	}

	@NotBlank
	@Pattern(regexp = "((ES)?\\d{8}[A-Z]{1}$)")
	public String getVAT() {
		return this.VAT;
	}

	public void setVAT(final String vAT) {
		this.VAT = vAT;
	}

	// Relationships ----------------------------------------------------------
}
