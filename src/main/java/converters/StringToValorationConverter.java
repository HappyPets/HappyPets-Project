
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ValorationRepository;
import domain.Valoration;

@Component
@Transactional
public class StringToValorationConverter implements Converter<String, Valoration> {

	@Autowired
	ValorationRepository	valorationRepository;


	@Override
	public Valoration convert(final String text) {

		Valoration result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.valorationRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
