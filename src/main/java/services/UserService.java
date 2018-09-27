
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.UserAccount;
import domain.AdoptionRequest;
import domain.Advertisement;
import domain.Comment;
import domain.Donation;
import domain.Inscription;
import domain.JobOffer;
import domain.Message;
import domain.Pet;
import domain.User;
import domain.Valoration;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository	userRepository;

	@Autowired
	private ActorService	actorService;


	// Constructor --------------------------------------------
	public UserService() {
		super();
	}

	// Crud methods -------------------------------------------
	public User create() {
		final User res = new User();
		res.setAdoptionRequests(new ArrayList<AdoptionRequest>());
		res.setAdvertisements(new ArrayList<Advertisement>());
		res.setComments(new ArrayList<Comment>());
		res.setDonations(new ArrayList<Donation>());
		res.setInscriptions(new ArrayList<Inscription>());
		res.setJobOffers(new ArrayList<JobOffer>());
		res.setPets(new ArrayList<Pet>());
		res.setReceivedMessages(new ArrayList<Message>());
		res.setSendedMessages(new ArrayList<Message>());
		res.setValorationsPosted(new ArrayList<Valoration>());
		res.setValorationsReceived(new ArrayList<Valoration>());
		final UserAccount useracc = new UserAccount();
		final Authority auth = new Authority();
		auth.setAuthority(Authority.USER);
		useracc.addAuthority(auth);
		res.setUserAccount(useracc);
		return res;
	}

	public User saveNewUser(final User user) {
		User saved;
		Assert.notNull(user);
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password = user.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		user.getUserAccount().setPassword(password);
		saved = this.userRepository.save(user);
		return saved;
	}

	public User save(final User user) {
		Assert.notNull(user);
		Assert.isTrue(this.actorService.isUser());
		User saved;
		saved = this.userRepository.save(user);
		return saved;
	}

	public User findOne(final int userId) {
		final User res = this.userRepository.findOne(userId);
		return res;
	}

	public Collection<User> findAll() {
		final Collection<User> res = this.userRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Queries -------------------------------------------
	//Usuario mejor valorado.
	//	public Collection<User> userMostValued() {
	//		return this.userRepository.userMostValued();
	//	}
}
