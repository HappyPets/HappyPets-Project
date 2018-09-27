
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.VetRepository;
import security.Authority;
import security.UserAccount;
import domain.Advertisement;
import domain.Comment;
import domain.Donation;
import domain.Valoration;
import domain.Vet;

@Service
@Transactional
public class VetService {

	@Autowired
	private VetRepository	vetRepository;

	@Autowired
	private ActorService	actorService;


	// Constructor --------------------------------------------
	public VetService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Vet create() {
		final Vet res = new Vet();
		res.setAdvertisements(new ArrayList<Advertisement>());
		res.setComments(new ArrayList<Comment>());
		res.setDonations(new ArrayList<Donation>());
		res.setValorationsReceived(new ArrayList<Valoration>());
		final UserAccount useracc = new UserAccount();
		final Authority auth = new Authority();
		auth.setAuthority(Authority.VET);
		useracc.addAuthority(auth);
		res.setUserAccount(useracc);
		return res;
	}

	public Vet saveNewVet(final Vet vet) {
		Vet saved;
		Assert.notNull(vet);
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password = vet.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		vet.getUserAccount().setPassword(password);
		saved = this.vetRepository.save(vet);
		return saved;
	}

	public Vet save(final Vet vet) {
		Assert.notNull(vet);
		Assert.isTrue(this.actorService.isVet());
		Vet saved;
		saved = this.vetRepository.save(vet);
		return saved;
	}

	public Vet findOne(final int vetId) {
		final Vet res = this.vetRepository.findOne(vetId);
		return res;
	}

	public Collection<Vet> findAll() {
		final Collection<Vet> res = this.vetRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Queries -------------------------------------------
	//Media de anuncios por veterinario.
	public Double avgAvertisementsPerVet() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.vetRepository.avgAvertisementsPerVet();
	}

	//Veterinario mejor valorado.
	public Vet vetMostValued() {
		Assert.isTrue(this.actorService.isAdmin());
		final List<Vet> vets = this.vetRepository.vetMostValued();
		final Vet res = vets.get(0);
		return res;
	}
}
