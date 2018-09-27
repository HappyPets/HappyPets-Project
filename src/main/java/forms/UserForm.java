
package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.User;

public class UserForm {

	int	id, version;
	private String	name, surname, DNI, email, phone, address, city, password, repeatPassword, username, biography, picture;
	private Date	birthDate;
	private boolean	acceptLOPD	= false;


	public UserForm() {
		super();
	}

	public UserForm(final User user) {
		this.id = user.getId();
		this.version = user.getVersion();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.DNI = user.getDNI();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.city = user.getCity();
		this.biography = user.getBiography();
		this.picture = user.getPicture();
		this.birthDate = user.getBirthDate();
		this.username = user.getUserAccount().getUsername();
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

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
	@Size(min = 5, max = 32)
	@SafeHtml
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml
	public String getRepeatPassword() {
		return this.repeatPassword;
	}

	public void setRepeatPassword(final String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@SafeHtml
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public boolean getAcceptLOPD() {
		return this.acceptLOPD;
	}

	public void setAcceptLOPD(final boolean acceptLOPD) {
		this.acceptLOPD = acceptLOPD;
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

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "(^\\d{8}[\\-][A-Z]$)")
	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(final String dNI) {
		this.DNI = dNI;
	}

	@SafeHtml
	public String getBiography() {
		return this.biography;
	}

	public void setBiography(final String biography) {
		this.biography = biography;
	}

	@URL
	@NotBlank
	@SafeHtml
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

}
