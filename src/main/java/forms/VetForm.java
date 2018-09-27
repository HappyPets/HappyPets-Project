
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
import org.springframework.format.annotation.DateTimeFormat;

import domain.Vet;

public class VetForm {

	int	id, version;
	private String	name, surname, DNI, email, phone, address, city, password, repeatPassword, username, licenseNumber;
	private Date	birthDate;
	private boolean	acceptLOPD	= false;


	public VetForm() {
		super();
	}

	public VetForm(final Vet vet) {
		this.id = vet.getId();
		this.version = vet.getVersion();
		this.name = vet.getName();
		this.surname = vet.getSurname();
		this.setDNI(vet.getDNI());
		this.email = vet.getEmail();
		this.phone = vet.getPhone();
		this.address = vet.getAddress();
		this.city = vet.getCity();
		this.licenseNumber = vet.getLicenseNumber();
		this.birthDate = vet.getBirthDate();
		this.username = vet.getUserAccount().getUsername();
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
	@NotBlank
	@Pattern(regexp = "(^\\d{10}$)")
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(final String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}
