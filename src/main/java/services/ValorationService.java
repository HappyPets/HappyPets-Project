
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ValorationRepository;
import domain.Inscription;
import domain.JobOffer;
import domain.Status;
import domain.SubActor;
import domain.User;
import domain.Valoration;

@Service
@Transactional
public class ValorationService {

	@Autowired
	private ValorationRepository	valorationRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private SubActorService			subActorService;


	// Constructor --------------------------------------------
	public ValorationService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Valoration create() {
		Assert.isTrue(this.actorService.isUser());
		final Valoration valoration = new Valoration();
		final User logued = (User) this.actorService.findByPrincipal();
		valoration.setValorator(logued);
		return valoration;
	}

	public Valoration save(Valoration valoration) {
		Assert.isTrue(this.actorService.isUser());
		Assert.notNull(valoration);
		valoration = this.valorationRepository.save(valoration);
		return valoration;
	}

	public Valoration findOne(final int valorationId) {
		final Valoration res = this.valorationRepository.findOne(valorationId);
		return res;
	}

	public Collection<Valoration> findAll() {
		final Collection<Valoration> res = this.valorationRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método que devuelve la media de valoración de un SubActor
	public int valorationMedia(final int subActorId) {
		Assert.isTrue(this.actorService.isUser() || this.actorService.isVet() || this.actorService.isCompany());
		final SubActor subActor = this.subActorService.findOne(subActorId);
		final Collection<Valoration> valorations = subActor.getValorationsReceived();
		int total = 0;
		int res = 0;
		for (final Valoration valoration : valorations)
			total = total + valoration.getStars();
		if (subActor.getValorationsReceived().size() > 0)
			res = total / subActor.getValorationsReceived().size();
		return res;
	}

	// Método que devuelve si el actor logueado puede valorar a otro
	public boolean canValorate(final int subActorId) {
		boolean res = true;
		boolean valorated = false;
		boolean acceptedInscription = false;
		if (!this.actorService.isUser())
			res = false;
		else {
			// Comprobación de si el usuario logueado ha valorado ya antes, o no, al SubActor
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<Valoration> valorations = logued.getValorationsPosted();
			for (final Valoration valoration : valorations)
				if (valoration.getValorated().getId() == subActorId) {
					valorated = true;
					break;
				}
			// Comprobación de si el usuario logueado tiene una oferta de trabajo con una inscripción del SubActor aprobada
			final Collection<JobOffer> jobOffers = logued.getJobOffers();
			for (final JobOffer jobOffer : jobOffers) {
				final Collection<Inscription> inscriptions = jobOffer.getInscriptions();
				for (final Inscription inscription : inscriptions)
					if (inscription.getInscripter().getId() == subActorId && inscription.getStatus().equals(Status.ACCEPTED)) {
						acceptedInscription = true;
						break;
					}
			}
			res = valorated == false && acceptedInscription == true;
		}
		return res;
	}

}
