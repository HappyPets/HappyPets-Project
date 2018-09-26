
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SubActorRepository;
import domain.SubActor;

@Component
@Transactional
public class StringToSubActorConverter implements Converter<String, SubActor> {

	@Autowired
	SubActorRepository	subActorRepository;


	@Override
	public SubActor convert(final String text) {

		SubActor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.subActorRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
