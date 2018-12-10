
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CauseRepository;
import domain.Cause;
import domain.Donation;

@Service
@Transactional
public class CauseService {

	@Autowired
	private CauseRepository	causeRepository;

	@Autowired
	private ActorService	actorService;


	// Constructor --------------------------------------------
	public CauseService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Cause create() {
		Assert.isTrue(this.actorService.isAdmin());
		final Cause res = new Cause();
		res.setDonations(new ArrayList<Donation>());
		res.setIsActive(true);
		return res;
	}

	public Cause save(final Cause cause) {
		Assert.notNull(cause);
		Assert.isTrue(cause.getDonations().size() == 0);
		Assert.isTrue(this.actorService.isAdmin());
		final Cause res = this.causeRepository.save(cause);
		return res;
	}

	public Cause saveDonations(final Cause cause) {
		Assert.notNull(cause);
		Assert.isTrue(this.actorService.isAdmin() || this.actorService.isCompany() || this.actorService.isUser() || this.actorService.isVet());
		final Cause res = this.causeRepository.save(cause);
		return res;
	}

	public Cause saveDeactive(final Cause cause) {
		Assert.notNull(cause);
		Assert.isTrue(this.actorService.isAdmin());
		final Cause res = this.causeRepository.save(cause);
		return res;
	}

	public Cause findOne(final int causeId) {
		final Cause res = this.causeRepository.findOne(causeId);
		return res;
	}

	public Collection<Cause> findAll() {
		final Collection<Cause> res = this.causeRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------
	//Devuelve las causas activas
	public Collection<Cause> getActiveCauses() {
		final Collection<Cause> res = new ArrayList<Cause>();
		for (final Cause c : this.findAll())
			if (c.getIsActive())
				res.add(c);
		return res;
	}

	//Devuelve las causas no activas
	public Collection<Cause> getDeactiveCauses() {
		Assert.isTrue(this.actorService.isAdmin());
		final Collection<Cause> res = new ArrayList<Cause>();
		for (final Cause c : this.findAll())
			if (c.getIsActive() == false)
				res.add(c);
		return res;
	}
	//Desactivar una causa por el admin
	public void deactiveCause(final Cause cause) {
		Assert.isTrue(this.actorService.isAdmin());
		cause.setIsActive(false);
		this.saveDeactive(cause);
	}
	// Queries -------------------------------------------
	//Causa con más donaciones.
	public Collection<Cause> causeWithMoreDonations() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.causeRepository.causeWithMoreDonations();
	}

}
