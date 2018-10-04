
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdoptionRequestRepository;
import domain.AdoptionRequest;
import domain.Message;
import domain.Pet;
import domain.Status;
import domain.User;

@Service
@Transactional
public class AdoptionRequestService {

	@Autowired
	private AdoptionRequestRepository	adoptionRequestRepository;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private PetService					petService;

	@Autowired
	private MessageService				messageService;


	// Constructor --------------------------------------------
	public AdoptionRequestService() {
		super();
	}

	// Crud methods -------------------------------------------
	public AdoptionRequest create() {
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final AdoptionRequest adoptionRequest = new AdoptionRequest();
		adoptionRequest.setStatus(Status.PENDING);
		adoptionRequest.setAdopter(logued);
		return adoptionRequest;
	}

	public AdoptionRequest save(AdoptionRequest adoptionRequest) {
		Assert.notNull(adoptionRequest);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(adoptionRequest.getAdopter()) || logued.equals(adoptionRequest.getPet().getOwner()));
		adoptionRequest.setRequestMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		adoptionRequest = this.adoptionRequestRepository.save(adoptionRequest);
		return adoptionRequest;
	}

	public AdoptionRequest findOne(final int adoptionRequestId) {
		final AdoptionRequest res = this.adoptionRequestRepository.findOne(adoptionRequestId);
		return res;
	}

	public Collection<AdoptionRequest> findAll() {
		final Collection<AdoptionRequest> res = this.adoptionRequestRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método para solicitar adoptar a una mascota
	public AdoptionRequest requestAdoption(final int petId) {
		Assert.isTrue(petId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final Pet pet = this.petService.findOne(petId);
		Assert.isTrue(pet.isInAdoption());
		Assert.isTrue(!logued.equals(pet.getOwner()));
		final Collection<AdoptionRequest> adoptionRequests = logued.getAdoptionRequests();
		for (final AdoptionRequest adoptionRequestAux : adoptionRequests) {
			Assert.isTrue(!(adoptionRequestAux.getPet().equals(pet) && adoptionRequestAux.getStatus().equals(Status.PENDING)));
			Assert.isTrue(!(adoptionRequestAux.getPet().equals(pet) && adoptionRequestAux.getStatus().equals(Status.ACCEPTED)));
			Assert.isTrue(!(adoptionRequestAux.getPet().equals(pet) && adoptionRequestAux.getStatus().equals(Status.DENIED)));
		}
		AdoptionRequest adoptionRequest = this.create();
		adoptionRequest.setPet(pet);
		adoptionRequest = this.save(adoptionRequest);

		Message message = this.messageService.create();
		message.setReceiver(pet.getOwner());
		message.setSubject("Nueva solicitud de adopción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha solicitado adoptar a " + pet.getName());
		message = this.messageService.save(message);

		return adoptionRequest;
	}

	// Método para cancelar una solicitud para adoptar a una mascota
	public AdoptionRequest cancelAdoptionRequest(final int adoptionRequestId) {
		Assert.isTrue(adoptionRequestId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		AdoptionRequest adoptionRequest = this.findOne(adoptionRequestId);
		Assert.isTrue(adoptionRequest.getStatus().equals(Status.PENDING));
		final Pet pet = adoptionRequest.getPet();
		Assert.isTrue(!logued.equals(pet.getOwner()));

		adoptionRequest.setStatus(Status.CANCELLED);
		adoptionRequest = this.save(adoptionRequest);

		Message message = this.messageService.create();
		message.setReceiver(pet.getOwner());
		message.setSubject("Cancelada solicitud de adopción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha cancelado su solicitud para adoptar a " + pet.getName());
		message = this.messageService.save(message);

		return adoptionRequest;
	}

	// Método para aceptar una solicitud de adopción a una mascota
	public AdoptionRequest acceptAdoptionRequest(final int adoptionRequestId) {
		Assert.isTrue(adoptionRequestId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		AdoptionRequest adoptionRequest = this.findOne(adoptionRequestId);
		Assert.isTrue(adoptionRequest.getStatus().equals(Status.PENDING));
		Pet pet = adoptionRequest.getPet();
		Assert.isTrue(logued.equals(pet.getOwner()));

		adoptionRequest.setStatus(Status.ACCEPTED);
		adoptionRequest = this.save(adoptionRequest);

		final Collection<AdoptionRequest> allPetAdoptionRequest = pet.getAdoptionRequests();
		for (final AdoptionRequest adoptionRequestAux : allPetAdoptionRequest)
			if (adoptionRequestAux.getStatus().equals(Status.PENDING))
				this.denyAdoptionRequest(adoptionRequestAux.getId());

		Message message = this.messageService.create();
		message.setReceiver(adoptionRequest.getAdopter());
		message.setSubject("Aceptada solicitud de adopción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha aceptado tu solicitud para adoptar a " + pet.getName());
		message = this.messageService.save(message);

		pet.setInAdoption(false);
		pet = this.petService.save(pet);

		return adoptionRequest;
	}

	// Método para rechazar una solicitud de adopción a una mascota
	public AdoptionRequest denyAdoptionRequest(final int adoptionRequestId) {
		Assert.isTrue(adoptionRequestId != 0);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		AdoptionRequest adoptionRequest = this.findOne(adoptionRequestId);
		Assert.isTrue(adoptionRequest.getStatus().equals(Status.PENDING));
		final Pet pet = adoptionRequest.getPet();
		Assert.isTrue(logued.equals(pet.getOwner()));

		adoptionRequest.setStatus(Status.DENIED);
		adoptionRequest = this.save(adoptionRequest);

		Message message = this.messageService.create();
		message.setReceiver(adoptionRequest.getAdopter());
		message.setSubject("Rechazada solicitud de adopción");
		message.setText(logued.getName() + " " + logued.getSurname() + " ha rechazado tu solicitud para adoptar a " + pet.getName());
		message = this.messageService.save(message);

		return adoptionRequest;
	}

	// Método para rechazar todas las solicitudes de adopción de una mascota
	public void denyAllAdoptionRequests(final int petId) {
		Assert.isTrue(petId != 0);
		Assert.isTrue(this.actorService.isUser());
		final Pet pet = this.petService.findOne(petId);
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(pet.getOwner().equals(logued));
		final Collection<AdoptionRequest> adoptionRequests = pet.getAdoptionRequests();
		for (final AdoptionRequest adoptionRequest : adoptionRequests)
			if (adoptionRequest.getStatus().equals(Status.PENDING))
				this.denyAdoptionRequest(adoptionRequest.getId());
	}

	// Queries -------------------------------------------
	//Nº de solicitudes de adopción aceptadas.
	public Integer nAdoptionRequestAccepted() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.adoptionRequestRepository.nAdoptionRequestAccepted();
	}

	//Nº de solicitudes de adopción denegadas.
	public Integer nAdoptionRequestDenied() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.adoptionRequestRepository.nAdoptionRequestDenied();
	}

	//Ratio de las solicitudes de adopción aceptadas frente a las denegadas.
	public Double ratioAdoptionRequestAcceptedVSDenied() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.adoptionRequestRepository.ratioAdoptionRequestAcceptedVSDenied();
	}

}
