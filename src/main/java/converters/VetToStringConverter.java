
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Vet;

@Component
@Transactional
public class VetToStringConverter implements Converter<Vet, String> {

	@Override
	public String convert(final Vet vet) {
		String result;
		if (vet == null)
			result = null;
		else
			result = String.valueOf(vet.getId());

		return result;
	}

}
