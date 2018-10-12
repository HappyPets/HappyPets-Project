
package forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import domain.PriceAdvertisement;

public class PriceAdvertisementForm {

	int		id, version;
	double	value;


	public PriceAdvertisementForm() {
		super();
	}

	public PriceAdvertisementForm(final PriceAdvertisement priceAdvertisement) {
		this.id = priceAdvertisement.getId();
		this.version = priceAdvertisement.getVersion();
		this.value = priceAdvertisement.getValue();
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

	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public double getValue() {
		return this.value;
	}

	public void setValue(final double value) {
		this.value = value;
	}
}
