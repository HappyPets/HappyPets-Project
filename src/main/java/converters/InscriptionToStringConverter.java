
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Inscription;

@Component
@Transactional
public class InscriptionToStringConverter implements Converter<Inscription, String> {

	@Override
	public String convert(final Inscription inscription) {
		String result;
		if (inscription == null)
			result = null;
		else
			result = String.valueOf(inscription.getId());

		return result;
	}

}
