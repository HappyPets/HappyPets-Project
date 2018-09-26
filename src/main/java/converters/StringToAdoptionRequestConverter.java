
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AdoptionRequestRepository;
import domain.AdoptionRequest;

@Component
@Transactional
public class StringToAdoptionRequestConverter implements Converter<String, AdoptionRequest> {

	@Autowired
	AdoptionRequestRepository	adoptionRequestRepository;


	@Override
	public AdoptionRequest convert(final String text) {

		AdoptionRequest result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.adoptionRequestRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
