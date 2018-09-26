
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class User extends SubActor {

	// Attributes -------------------------------------------------------------
	private String	picture, biography;


	// Getters and setters ----------------------------------------------------
	@URL
	@NotBlank
	@SafeHtml
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@SafeHtml
	public String getBiography() {
		return this.biography;
	}

	public void setBiography(final String biography) {
		this.biography = biography;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Message>			sendedMessages;
	private Collection<Message>			receivedMessages;
	private Collection<Inscription>		inscriptions;
	private Collection<JobOffer>		jobOffers;
	private Collection<Pet>				pets;
	private Collection<Valoration>		valorationsPosted;
	private Collection<AdoptionRequest>	adoptionRequests;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "sender")
	public Collection<Message> getSendedMessages() {
		return this.sendedMessages;
	}

	public void setSendedMessages(final Collection<Message> sendedMessages) {
		this.sendedMessages = sendedMessages;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "receiver")
	public Collection<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(final Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "inscripter")
	public Collection<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(final Collection<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "owner")
	public Collection<JobOffer> getJobOffers() {
		return this.jobOffers;
	}

	public void setJobOffers(final Collection<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "owner")
	public Collection<Pet> getPets() {
		return this.pets;
	}

	public void setPets(final Collection<Pet> pets) {
		this.pets = pets;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "valorator")
	public Collection<Valoration> getValorationsPosted() {
		return this.valorationsPosted;
	}

	public void setValorationsPosted(final Collection<Valoration> valorationsPosted) {
		this.valorationsPosted = valorationsPosted;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "adopter")
	public Collection<AdoptionRequest> getAdoptionRequests() {
		return this.adoptionRequests;
	}

	public void setAdoptionRequests(final Collection<AdoptionRequest> adoptionRequests) {
		this.adoptionRequests = adoptionRequests;
	}

}
