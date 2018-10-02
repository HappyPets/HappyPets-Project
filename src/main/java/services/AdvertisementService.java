
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import domain.Advertisement;
import domain.SubActor;

@Service
@Transactional
public class AdvertisementService {

	@Autowired
	private AdvertisementRepository	advertisementRepository;

	@Autowired
	private ActorService			actorService;


	// Constructor --------------------------------------------
	public AdvertisementService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Advertisement create() {
		Assert.isTrue(this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		final Advertisement res = new Advertisement();
		res.setTicker(this.generateTicker());
		res.setIsBanned(false);
		res.setSubActor((SubActor) this.actorService.findByPrincipal());
		return res;
	}

	public Advertisement save(final Advertisement advertisement) {
		Assert.isTrue(this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		Assert.notNull(advertisement);
		if (advertisement.getId() != 0) {
			Assert.isTrue(advertisement.getSubActor().getId() == this.actorService.findByPrincipal().getId());
			Assert.isTrue(!advertisement.getIsBanned());
		}
		final Advertisement saved = this.advertisementRepository.save(advertisement);
		return saved;
	}

	public void delete(final Advertisement advertisement) {
		Assert.isTrue(this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		final SubActor subActor = (SubActor) this.actorService.findByPrincipal();
		Assert.isTrue(subActor.getAdvertisements().contains(advertisement));
		this.advertisementRepository.delete(advertisement);
	}

	public Advertisement findOne(final int advertisementId) {
		final Advertisement res = this.advertisementRepository.findOne(advertisementId);
		return res;
	}

	public Collection<Advertisement> findAll() {
		final Collection<Advertisement> res = this.advertisementRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------
	//Devuelve los anuncios no prohibidos
	public Collection<Advertisement> getAdvertisementsNoBanned() {
		final Collection<Advertisement> res = new ArrayList<Advertisement>();
		for (final Advertisement a : this.findAll())
			if (!a.getIsBanned())
				res.add(a);
		return res;
	}

	//Genera ticker automático para el anuncio
	public String generateTicker() {
		String ticker = "";
		final int[] digits = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 0
		};
		final Date date = new Date();
		final String dateString = date.toString();
		final int year = Integer.valueOf(dateString.split(" ")[5]) % 100;

		final Random random = new Random();
		for (int i = 0; i < 4; i++)
			ticker = ticker + String.valueOf(digits[random.nextInt(digits.length)]);

		ticker = ticker + "-" + String.valueOf(year);
		ticker = this.uniqueTicker(ticker);
		return ticker;
	}

	//Comprueba que el ticker es único
	public String uniqueTicker(String ticker) {
		for (final Advertisement a : this.findAll())
			if (a.getTicker().equals(ticker))
				ticker = this.generateTicker();
		return ticker;
	}

	//Prohibe un anuncio
	public void banAdvertisement(final int advertisementId) {
		Assert.isTrue(this.actorService.isAdmin());
		final Advertisement advertisement = this.findOne(advertisementId);
		Assert.notNull(advertisement);
		Assert.isTrue(!advertisement.getIsBanned());
		advertisement.setIsBanned(true);
	}

}
