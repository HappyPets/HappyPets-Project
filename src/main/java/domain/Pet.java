
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Pet extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name, description, healthDescription, city, picture;
	private Double	weight, height;
	private Integer	age;
	private Genre	genre;
	private TypeAge	typeAge;
	private boolean	inAdoption;


	// Getters and setters ----------------------------------------------------

	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	@Min(0)
	@Max(100)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(final Genre genre) {
		this.genre = genre;
	}
	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public TypeAge getTypeAge() {
		return this.typeAge;
	}

	public void setTypeAge(final TypeAge typeAge) {
		this.typeAge = typeAge;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	@SafeHtml
	public String getHealthDescription() {
		return this.healthDescription;
	}

	public void setHealthDescription(final String healthDescription) {
		this.healthDescription = healthDescription;
	}

	@NotBlank
	@SafeHtml
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotBlank
	@SafeHtml
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotNull
	@Digits(integer = 3, fraction = 2)
	@Min(0)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(final Double weight) {
		this.weight = weight;
	}

	@NotNull
	@Digits(integer = 2, fraction = 2)
	@Min(0)
	public Double getHeight() {
		return this.height;
	}

	public void setHeight(final Double height) {
		this.height = height;
	}

	public boolean isInAdoption() {
		return this.inAdoption;
	}

	public void setInAdoption(final boolean inAdoption) {
		this.inAdoption = inAdoption;
	}


	// Relationships ----------------------------------------------------------
	private Category					category;
	private User						owner;
	private Collection<AdoptionRequest>	adoptionRequests;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getOwner() {
		return this.owner;
	}

	public void setOwner(final User owner) {
		this.owner = owner;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	public Collection<AdoptionRequest> getAdoptionRequests() {
		return this.adoptionRequests;
	}

	public void setAdoptionRequests(final Collection<AdoptionRequest> adoptionRequests) {
		this.adoptionRequests = adoptionRequests;
	}

}
