
package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import domain.JobOffer;
import domain.Pet;

public class JobOfferForm {

	// Attributes -------------------------------------------------------------
	private int	id, version;
	private String	title, description, city;
	private Date	startDate, endDate;
	private Double	salary;
	private Pet		pet;


	// Constructor ------------------------------------------------------------
	public JobOfferForm() {
		super();
	}

	public JobOfferForm(final JobOffer jobOffer) {
		this.id = jobOffer.getId();
		this.version = jobOffer.getVersion();
		this.title = jobOffer.getTitle();
		this.description = jobOffer.getDescription();
		this.city = jobOffer.getCity();
		this.startDate = jobOffer.getStartDate();
		this.endDate = jobOffer.getEndDate();
		this.salary = jobOffer.getSalary();
		this.pet = jobOffer.getPet();
	}

	// Getters and setters ----------------------------------------------------
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

	@NotBlank
	@SafeHtml
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@NotNull
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(final Double salary) {
		this.salary = salary;
	}

	@Valid
	@NotNull
	public Pet getPet() {
		return this.pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}

}
