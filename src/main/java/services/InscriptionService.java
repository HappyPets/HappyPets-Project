
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.InscriptionRepository;
import domain.Inscription;
import domain.JobOffer;
import domain.Message;
import domain.Status;
import domain.User;

@Service
@Transactional
public class InscriptionService {

	@Autowired
	private InscriptionRepository	inscriptionRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private JobOfferService			jobOfferService;

	@Autowired
	private MessageService			messageService;


	// Constructor --------------------------------------------
	public InscriptionService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Inscription create() {
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final Inscription inscription = new Inscription();
		inscription.setStatus(Status.PENDING);
		inscription.setInscripter(logued);
		return inscription;
	}

	public Inscription save(Inscription inscription) {
		Assert.isTrue(this.actorService.isUser());
		Assert.notNull(inscription);
		inscription.setInscriptionMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		inscription = this.inscriptionRepository.save(inscription);
		return inscription;
	}

	public void delete(final int inscriptionId) {
		Assert.isTrue(this.actorService.isUser());
		Assert.isTrue(inscriptionId != 0);
		final Inscription inscription = this.findOne(inscriptionId);
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(inscription.getJobOffer().getOwner().equals(logued));
		this.inscriptionRepository.delete(inscriptionId);
	}

	public Inscription findOne(final int inscriptionId) {
		final Inscription res = this.inscriptionRepository.findOne(inscriptionId);
		return res;
	}

	public Collection<Inscription> findAll() {
		final Collection<Inscription> res = this.inscriptionRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método para inscribirse a una oferta de trabajo
	public Inscription sendInscription(final int jobOfferId) {
		Assert.isTrue(jobOfferId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final JobOffer jobOffer = this.jobOfferService.findOne(jobOfferId);
		Assert.isTrue(!jobOffer.getIsClosed());
		Assert.isTrue(!logued.equals(jobOffer.getOwner()));
		Assert.isTrue(!jobOffer.getIsBanned());

		final Collection<Inscription> inscriptions = logued.getInscriptions();
		for (final Inscription inscriptionAux : inscriptions)
			Assert.isTrue(inscriptionAux.getJobOffer().getId() != jobOffer.getId() || (inscriptionAux.getJobOffer().getId() == jobOffer.getId() && (inscriptionAux.getStatus().equals(Status.PENDING) || inscriptionAux.getStatus().equals(Status.CANCELLED))));

		Inscription inscription = this.create();
		inscription.setJobOffer(jobOffer);
		inscription = this.save(inscription);

		Message message = this.messageService.create();
		message.setReceiver(jobOffer.getOwner());
		message.setSubject("Nueva inscripción");
		message.setText(logued.getName() + " " + logued.getSurname() + " se ha inscrito a la oferta de trabajo " + jobOffer.getTitle());
		message = this.messageService.save(message);

		return inscription;
	}
	// Método para cancelar una inscripción a una oferta de trabajo
	public Inscription cancelInscription(final int inscriptionId) {
		Assert.isTrue(inscriptionId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Inscription inscription = this.findOne(inscriptionId);
		Assert.isTrue(inscription.getStatus().equals(Status.PENDING));
		final JobOffer jobOffer = inscription.getJobOffer();
		Assert.isTrue(!logued.equals(jobOffer.getOwner()));

		inscription.setStatus(Status.CANCELLED);
		inscription = this.save(inscription);

		Message message = this.messageService.create();
		message.setReceiver(jobOffer.getOwner());
		message.setSubject("Inscripción cancelada");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha cancelado su inscripción a la oferta de trabajo " + jobOffer.getTitle());
		message = this.messageService.save(message);

		return inscription;
	}

	// Método para aceptar una inscripción a una oferta de trabajo
	public Inscription acceptInscription(final int inscriptionId) {
		Assert.isTrue(inscriptionId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Inscription inscription = this.findOne(inscriptionId);
		Assert.isTrue(inscription.getStatus().equals(Status.PENDING));
		JobOffer jobOffer = inscription.getJobOffer();
		Assert.isTrue(logued.equals(jobOffer.getOwner()));
		Assert.isTrue(!jobOffer.getIsBanned());

		inscription.setStatus(Status.ACCEPTED);
		inscription = this.save(inscription);

		final Collection<Inscription> allInscriptionsJobOffer = jobOffer.getInscriptions();
		for (final Inscription inscriptionAux : allInscriptionsJobOffer)
			if (inscriptionAux.getStatus().equals(Status.PENDING))
				this.denyInscription(inscriptionAux.getId());

		Message message = this.messageService.create();
		message.setReceiver(inscription.getInscripter());
		message.setSubject("Aceptada inscripción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha aceptado tu inscripción para la oferta de trabajo " + jobOffer.getTitle());
		message = this.messageService.save(message);

		jobOffer.setIsClosed(true);
		jobOffer = this.jobOfferService.saveAcceptInscription(jobOffer);

		return inscription;
	}

	// Método para rechazar inscripción a una oferta de trabajo
	public Inscription denyInscription(final int inscriptionId) {
		Assert.isTrue(inscriptionId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Inscription inscription = this.findOne(inscriptionId);
		Assert.isTrue(inscription.getStatus().equals(Status.PENDING));
		final JobOffer jobOffer = inscription.getJobOffer();
		Assert.isTrue(logued.equals(jobOffer.getOwner()));
		Assert.isTrue(!jobOffer.getIsBanned());

		inscription.setStatus(Status.DENIED);
		inscription = this.save(inscription);

		Message message = this.messageService.create();
		message.setReceiver(inscription.getInscripter());
		message.setSubject("Rechazada inscripción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha rechazado tu inscripción para la oferta de trabajo " + jobOffer.getTitle());
		message = this.messageService.save(message);

		return inscription;
	}
}
