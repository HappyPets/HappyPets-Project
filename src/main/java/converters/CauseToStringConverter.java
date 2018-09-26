
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Cause;

@Component
@Transactional
public class CauseToStringConverter implements Converter<Cause, String> {

	@Override
	public String convert(final Cause cause) {
		String result;
		if (cause == null)
			result = null;
		else
			result = String.valueOf(cause.getId());

		return result;
	}

}
