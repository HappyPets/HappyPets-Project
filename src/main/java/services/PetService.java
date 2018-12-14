
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PetRepository;
import domain.AdoptionRequest;
import domain.Category;
import domain.JobOffer;
import domain.Pet;
import domain.TabooWords;
import domain.User;
import forms.PetForm;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetRepository		petRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private JobOfferService		jobbOfferService;

	@Autowired
	private TabooWordsService	tabooWordsService;


	// Constructor --------------------------------------------
	public PetService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Pet create() {
		Assert.isTrue(this.actorService.isUser());
		final User owner = (User) this.actorService.findByPrincipal();
		final Pet pet = new Pet();
		pet.setAdoptionRequests(new ArrayList<AdoptionRequest>());
		pet.setOwner(owner);
		return pet;
	}

	public Pet save(Pet pet) {
		Assert.isTrue(this.actorService.isUser());
		Assert.notNull(pet);
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(pet.getOwner()));
		pet = this.petRepository.save(pet);
		return pet;
	}

	public void delete(final int petId) {
		Assert.isTrue(this.actorService.isUser());
		Assert.isTrue(petId != 0);
		final Pet pet = this.findOne(petId);
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(pet.getOwner()));
		final Collection<JobOffer> allJobOffers = this.jobbOfferService.findAll();
		for (final JobOffer jobOffer : allJobOffers)
			if (jobOffer.getPet().equals(pet))
				this.jobbOfferService.delete(jobOffer.getId());
		this.petRepository.delete(petId);
	}

	public Pet findOne(final int petId) {
		final Pet res = this.petRepository.findOne(petId);
		return res;
	}

	public Collection<Pet> findAll() {
		final Collection<Pet> res = this.petRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método que devuelve el conjunto de todas las Pets que están en adopción
	public Collection<Pet> getAllPetsInAdoption() {
		final Collection<Pet> res = new ArrayList<Pet>();
		final Collection<Pet> allPets = this.findAll();
		for (final Pet pet : allPets)
			if (pet.isInAdoption())
				res.add(pet);
		return res;
	}

	// Método que devuelve el conjunto de Pets de la categoría pasada por parámetro a partir del conjunto de Pets pasada también como parámetro
	public Collection<Pet> getPetsByCategory(final Category category, final Collection<Pet> pets) {
		final Collection<Pet> res = new ArrayList<Pet>();
		for (final Pet pet : pets)
			if (pet.getCategory().equals(category))
				res.add(pet);
		return res;
	}

	// Método que detecta tabooWords
	public Boolean hasTabooWords(final PetForm pf) {
		Assert.isTrue(this.actorService.isAuthenticated());
		Boolean res = false;
		final String name = pf.getName();
		final String description = pf.getDescription();
		final String healthdescription = pf.getHealthDescription();
		final TabooWords tabooWords = this.tabooWordsService.getTabooWords();
		final Collection<String> tw = tabooWords.getWords();
		for (final String s : tw)
			if (name.contains(s) || description.contains(s) || healthdescription.contains(s))
				res = true;

		return res;
	}
}
