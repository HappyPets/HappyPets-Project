
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.JobOfferRepository;
import domain.JobOffer;

@Component
@Transactional
public class StringToJobOfferConverter implements Converter<String, JobOffer> {

	@Autowired
	JobOfferRepository	jobOfferRepository;


	@Override
	public JobOffer convert(final String text) {

		JobOffer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.jobOfferRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
