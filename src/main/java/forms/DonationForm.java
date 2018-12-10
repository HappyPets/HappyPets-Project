
package forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

public class DonationForm {

	int				id, version, causeId;
	private Double	quantity;
	private Integer	expirationMonth, expirationYear, cvv;
	private String	holderName, number;
	private String	brandName;


	public DonationForm() {
		super();
	}

	// Getters and setters
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

	public int getCauseId() {
		return this.causeId;
	}

	public void setCauseId(final int causeId) {
		this.causeId = causeId;
	}

	@NotNull
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

	//Atributos para credit card
	@NotNull
	@Range(min = 1, max = 12)
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@NotNull
	@Min(2018)
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@NotNull
	@Range(min = 100, max = 999)
	public Integer getCvv() {
		return this.cvv;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

	@NotBlank
	@SafeHtml
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@CreditCardNumber
	@NotBlank
	@SafeHtml
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@NotBlank
	@SafeHtml
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
}
