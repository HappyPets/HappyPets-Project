
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

import domain.Company;

public class CompanyForm {

	int	id, version;
	private String	name, surname, DNI, email, phone, address, city, password, repeatPassword, username, businessName;
	private Date	birthDate;
	private String	VAT;
	private boolean	acceptLOPD	= false;


	public CompanyForm() {
		super();
	}

	public CompanyForm(final Company company) {
		this.id = company.getId();
		this.version = company.getVersion();
		this.name = company.getName();
		this.surname = company.getSurname();
		this.setDNI(company.getDNI());
		this.email = company.getEmail();
		this.phone = company.getPhone();
		this.address = company.getAddress();
		this.city = company.getCity();
		this.businessName = company.getBusinessName();
		this.VAT = company.getVAT();
		this.birthDate = company.getBirthDate();
		this.username = company.getUserAccount().getUsername();
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
	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(final String businessName) {
		this.businessName = businessName;
	}

	@NotBlank
	@Pattern(regexp = "((ES)?\\d{8}[A-Z]{1}$)")
	public String getVAT() {
		return this.VAT;
	}

	public void setVAT(final String VAT) {
		this.VAT = VAT;
	}
}
