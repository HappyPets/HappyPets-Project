
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PriceAdvertisement;

@Component
@Transactional
public class PriceAdvertisementToStringConverter implements Converter<PriceAdvertisement, String> {

	@Override
	public String convert(final PriceAdvertisement priceAdvertisement) {
		String result;
		if (priceAdvertisement == null)
			result = null;
		else
			result = String.valueOf(priceAdvertisement.getId());

		return result;
	}
}
