
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PriceAdvertisementRepository;
import domain.PriceAdvertisement;

@Component
@Transactional
public class StringToPriceAdvertisementConverter implements Converter<String, PriceAdvertisement> {

	@Autowired
	PriceAdvertisementRepository	priceAdvertisementRepository;


	@Override
	public PriceAdvertisement convert(final String text) {

		PriceAdvertisement result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.priceAdvertisementRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
