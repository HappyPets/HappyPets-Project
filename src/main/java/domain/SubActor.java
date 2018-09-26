
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public abstract class SubActor extends Actor {

	// Attributes -------------------------------------------------------------
	private String	name, surname, DNI, email, phone, address, city;
	private Date	birthDate;


	// Getters and setters ----------------------------------------------------
	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@SafeHtml
	@NotBlank
	@Pattern(regexp = "(^\\d{8}[\\-][A-Z]$)")
	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(final String dNI) {
		this.DNI = dNI;
	}

	@NotBlank
	@SafeHtml
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@SafeHtml
	@Pattern(regexp = "(^$)|(^[+]?\\d+$)")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@SafeHtml
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotBlank
	@SafeHtml
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Donation>		donations;
	private Collection<Valoration>		valorationsReceived;
	private Collection<Comment>			comments;
	private Collection<Advertisement>	advertisements;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "valorated")
	public Collection<Valoration> getValorationsReceived() {
		return this.valorationsReceived;
	}

	public void setValorationsReceived(final Collection<Valoration> valorationsReceived) {
		this.valorationsReceived = valorationsReceived;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "donor")
	public Collection<Donation> getDonations() {
		return this.donations;
	}

	public void setDonations(final Collection<Donation> donations) {
		this.donations = donations;
	}
	@Valid
	@NotNull
	@OneToMany(mappedBy = "subActor")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	@Valid
	@NotNull
	@OneToMany(mappedBy = "subActor")
	public Collection<Advertisement> getAdvertisements() {
		return this.advertisements;
	}

	public void setAdvertisements(final Collection<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

}
