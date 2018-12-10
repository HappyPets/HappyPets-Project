
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DonationRepository;
import domain.Cause;
import domain.Donation;
import domain.SubActor;

@Service
@Transactional
public class DonationService {

	@Autowired
	private DonationRepository	donationRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CauseService		causeService;


	// Constructor --------------------------------------------
	public DonationService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Donation create(final Cause cause) {
		Assert.isTrue(this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		Assert.notNull(cause);
		final Donation res = new Donation();
		res.setCausa(cause);
		res.setDonationMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		res.setDonor((SubActor) this.actorService.findByPrincipal());
		return res;
	}

	public Donation save(final Donation donation) {
		Assert.isTrue(this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		Assert.notNull(donation);
		Assert.isTrue(this.causeService.getActiveCauses().contains(donation.getCausa()));
		donation.setDonationMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		final Donation saved = this.donationRepository.save(donation);

		final Cause cause = donation.getCausa();
		cause.getDonations().add(donation);
		this.causeService.saveDonations(cause);

		return saved;
	}

	public void delete(final int donationId) {
		Assert.isTrue(this.actorService.isAdmin());
		Assert.isTrue(donationId != 0);
		this.donationRepository.delete(donationId);
	}

	public Donation findOne(final int donationId) {
		final Donation res = this.donationRepository.findOne(donationId);
		return res;
	}

	public Collection<Donation> findAll() {
		final Collection<Donation> res = this.donationRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

}
