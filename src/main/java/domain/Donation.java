
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Donation extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date		donationMoment;
	private Double		quantity;
	private CreditCard	creditCard;


	// Getters and setters ----------------------------------------------------

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	public Date getDonationMoment() {
		return this.donationMoment;
	}

	public void setDonationMoment(final Date donationMoment) {
		this.donationMoment = donationMoment;
	}

	@NotNull
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	// Relationships ----------------------------------------------------------
	private SubActor	donor;
	private Cause		causa;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public SubActor getDonor() {
		return this.donor;
	}

	public void setDonor(final SubActor donor) {
		this.donor = donor;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Cause getCausa() {
		return this.causa;
	}

	public void setCausa(final Cause causa) {
		this.causa = causa;
	}
}
