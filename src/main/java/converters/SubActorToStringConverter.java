
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SubActor;

@Component
@Transactional
public class SubActorToStringConverter implements Converter<SubActor, String> {

	@Override
	public String convert(final SubActor subActor) {
		String result;
		if (subActor == null)
			result = null;
		else
			result = String.valueOf(subActor.getId());

		return result;
	}

}
