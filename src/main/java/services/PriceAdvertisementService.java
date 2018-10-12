
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PriceAdvertisementRepository;
import domain.PriceAdvertisement;

@Service
@Transactional
public class PriceAdvertisementService {

	@Autowired
	private PriceAdvertisementRepository	priceAdvertisementRepository;

	@Autowired
	private ActorService					actorService;


	// Constructor --------------------------------------------
	public PriceAdvertisementService() {
		super();
	}

	// Crud methods -------------------------------------------
	public PriceAdvertisement save(final PriceAdvertisement priceAdvertisement) {
		Assert.isTrue(this.actorService.isAdmin());
		Assert.notNull(priceAdvertisement);
		final PriceAdvertisement saved = this.priceAdvertisementRepository.save(priceAdvertisement);
		return saved;
	}

	public PriceAdvertisement getPriceAdvertisement() {
		Assert.isTrue(this.actorService.isAuthenticated());
		final PriceAdvertisement res;
		final List<PriceAdvertisement> list = (List<PriceAdvertisement>) this.findAll();
		res = list.get(0);
		return res;
	}

	public PriceAdvertisement findOne(final int priceAdvertisementId) {
		final PriceAdvertisement res = this.priceAdvertisementRepository.findOne(priceAdvertisementId);
		return res;
	}

	public Collection<PriceAdvertisement> findAll() {
		final Collection<PriceAdvertisement> res = this.priceAdvertisementRepository.findAll();
		return res;
	}
}
