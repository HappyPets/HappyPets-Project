
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Advertisement extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String		ticker, banner, targetPage;
	private CreditCard	creditCard;
	private boolean		isBanned;


	// Getters and setters ----------------------------------------------------
	@NotBlank
	@SafeHtml
	@Column(unique = true)
	@Pattern(regexp = "^\\d{4}[\\-]\\d{2}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	@SafeHtml
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@SafeHtml
	@URL
	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(final String targetPage) {
		this.targetPage = targetPage;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public boolean getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(final boolean isBanned) {
		this.isBanned = isBanned;
	}


	// Relationships ----------------------------------------------------------
	private SubActor	subActor;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public SubActor getSubActor() {
		return this.subActor;
	}

	public void setSubActor(final SubActor subActor) {
		this.subActor = subActor;
	}

}
