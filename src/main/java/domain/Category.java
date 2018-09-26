
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

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;


	// Getters and setters ----------------------------------------------------
	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------
	private Collection<Pet>	pets;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "category")
	public Collection<Pet> getPets() {
		return this.pets;
	}

	public void setPets(final Collection<Pet> pets) {
		this.pets = pets;
	}
}
