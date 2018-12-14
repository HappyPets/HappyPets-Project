
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.JobOfferRepository;
import domain.Inscription;
import domain.JobOffer;
import domain.Message;
import domain.Status;
import domain.TabooWords;
import domain.User;
import forms.JobOfferForm;

@Service
@Transactional
public class JobOfferService {

	@Autowired
	private JobOfferRepository	jobOfferRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private InscriptionService	inscriptionService;

	@Autowired
	private MessageService		messageService;

	@Autowired
	private TabooWordsService	tabooWordsService;


	// Constructor --------------------------------------------
	public JobOfferService() {
		super();
	}

	// Crud methods -------------------------------------------
	public JobOffer create() {
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final JobOffer jobOffer = new JobOffer();
		jobOffer.setInscriptions(new ArrayList<Inscription>());
		jobOffer.setOwner(logued);
		jobOffer.setIsClosed(false);
		jobOffer.setIsBanned(false);
		return jobOffer;
	}

	public JobOffer save(JobOffer jobOffer) {
		Assert.isTrue(this.actorService.isUser() || this.actorService.isAdmin());
		Assert.notNull(jobOffer);
		if (jobOffer.getId() != 0 && this.actorService.isUser()) {
			final User user = (User) this.actorService.findByPrincipal();
			Assert.isTrue(jobOffer.getOwner().getId() == user.getId());
			Assert.isTrue(jobOffer.getInscriptions().isEmpty());
		}
		Assert.isTrue(jobOffer.getEndDate().after(jobOffer.getStartDate()));
		jobOffer = this.jobOfferRepository.save(jobOffer);
		return jobOffer;
	}

	public JobOffer saveAcceptInscription(JobOffer jobOffer) {
		Assert.isTrue(this.actorService.isUser() || this.actorService.isAdmin());
		Assert.notNull(jobOffer);
		jobOffer = this.jobOfferRepository.save(jobOffer);
		return jobOffer;
	}

	public void delete(final int jobOfferId) {
		Assert.isTrue(this.actorService.isUser());
		Assert.isTrue(jobOfferId != 0);
		Assert.isTrue(!this.hasAcceptedInscriptions(jobOfferId));
		final JobOffer jobOffer = this.findOne(jobOfferId);
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(jobOffer.getOwner()));
		final Collection<Inscription> allJobOfferInscription = jobOffer.getInscriptions();
		for (final Inscription inscription : allJobOfferInscription) {
			this.inscriptionService.delete(inscription.getId());
			Message message = this.messageService.create();
			message.setReceiver(inscription.getInscripter());
			message.setSubject("Oferta de trabajo eliminada");
			message.setText(logued.getName() + " " + logued.getSurname() + " ha elimiado la oferta de trabajo. Lo sentimos");
			message = this.messageService.save(message);
		}
		this.jobOfferRepository.delete(jobOfferId);
	}

	public JobOffer findOne(final int jobOfferId) {
		final JobOffer res = this.jobOfferRepository.findOne(jobOfferId);
		return res;
	}

	public Collection<JobOffer> findAll() {
		final Collection<JobOffer> res = this.jobOfferRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método que devuelve si la oferta de trabajo tiene inscripciones aceptadas o no
	public boolean hasAcceptedInscriptions(final int jobOfferId) {
		final JobOffer jobOffer = this.findOne(jobOfferId);
		final Collection<Inscription> inscriptions = jobOffer.getInscriptions();
		boolean res = false;
		for (final Inscription inscription : inscriptions)
			if (inscription.getStatus().equals(Status.ACCEPTED)) {
				res = true;
				break;
			}
		return res;
	}

	// Método que bannea una oferta de trabajo.
	public JobOffer banJobOffer(final int jobOfferId) {
		Assert.isTrue(this.actorService.isAdmin());
		Assert.isTrue(jobOfferId != 0);
		JobOffer jobOffer = this.findOne(jobOfferId);
		jobOffer.setIsBanned(true);
		jobOffer = this.save(jobOffer);
		return jobOffer;
	}

	// Método que devuelve las ofertas de trabajo baneadas.
	public Collection<JobOffer> getBannedJobOffers() {
		Assert.isTrue(this.actorService.isAdmin() || this.actorService.isUser());
		final Collection<JobOffer> res = new ArrayList<JobOffer>();
		final Collection<JobOffer> allJobOffers = this.findAll();
		for (final JobOffer jobOffer : allJobOffers)
			if (jobOffer.getIsBanned())
				res.add(jobOffer);
		return res;
	}

	// Método que detecta tabooWords
	public Boolean hasTabooWords(final JobOfferForm jof) {
		Assert.isTrue(this.actorService.isAuthenticated());
		Boolean res = false;
		final String title = jof.getTitle();
		final String description = jof.getDescription();
		final TabooWords tabooWords = this.tabooWordsService.getTabooWords();
		final Collection<String> tw = tabooWords.getWords();
		for (final String s : tw)
			if (title.contains(s) || description.contains(s))
				res = true;

		return res;
	}

}
