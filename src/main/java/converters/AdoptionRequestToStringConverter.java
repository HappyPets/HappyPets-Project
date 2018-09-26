
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.AdoptionRequest;

@Component
@Transactional
public class AdoptionRequestToStringConverter implements Converter<AdoptionRequest, String> {

	@Override
	public String convert(final AdoptionRequest adoptionRequest) {
		String result;
		if (adoptionRequest == null)
			result = null;
		else
			result = String.valueOf(adoptionRequest.getId());

		return result;
	}

}
