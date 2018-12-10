
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Cause extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String		title, description, picture;
	private Priority	priority;
	private boolean		isActive;


	// Getters and setters ----------------------------------------------------
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	@SafeHtml
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final boolean isActive) {
		this.isActive = isActive;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Donation>	donations;


	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Donation> getDonations() {
		return this.donations;
	}

	public void setDonations(final Collection<Donation> donations) {
		this.donations = donations;
	}

}
