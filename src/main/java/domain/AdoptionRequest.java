
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class AdoptionRequest extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date	requestMoment;
	private Status	status;


	// Getters and setters ----------------------------------------------------
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	public Date getRequestMoment() {
		return this.requestMoment;
	}

	public void setRequestMoment(final Date requestMoment) {
		this.requestMoment = requestMoment;
	}

	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}


	// Relationships ----------------------------------------------------------
	private User	adopter;
	private Pet		pet;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getAdopter() {
		return this.adopter;
	}

	public void setAdopter(final User adopter) {
		this.adopter = adopter;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Pet getPet() {
		return this.pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}

}
