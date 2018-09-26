
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
public class Inscription extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date	inscriptionMoment;
	private Status	status;


	// Getters and setters ----------------------------------------------------
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	public Date getInscriptionMoment() {
		return this.inscriptionMoment;
	}

	public void setInscriptionMoment(final Date inscriptionMoment) {
		this.inscriptionMoment = inscriptionMoment;
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
	private User		inscripter;
	private JobOffer	jobOffer;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getInscripter() {
		return this.inscripter;
	}

	public void setInscripter(final User inscripter) {
		this.inscripter = inscripter;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public JobOffer getJobOffer() {
		return this.jobOffer;
	}

	public void setJobOffer(final JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

}
