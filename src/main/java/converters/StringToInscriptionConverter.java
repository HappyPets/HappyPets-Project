
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.InscriptionRepository;
import domain.Inscription;

@Component
@Transactional
public class StringToInscriptionConverter implements Converter<String, Inscription> {

	@Autowired
	InscriptionRepository	inscriptionRepository;


	@Override
	public Inscription convert(final String text) {

		Inscription result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.inscriptionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
