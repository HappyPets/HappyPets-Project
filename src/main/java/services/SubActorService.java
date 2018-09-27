
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubActorRepository;
import domain.SubActor;

@Service
@Transactional
public class SubActorService {

	@Autowired
	private SubActorRepository	subActorRepository;

	@Autowired
	private ActorService		actorService;


	// Constructor --------------------------------------------
	public SubActorService() {
		super();
	}

	// Crud methods -------------------------------------------
	public SubActor findOne(final int actorId) {
		final SubActor res = this.subActorRepository.findOne(actorId);
		return res;
	}

	public Collection<SubActor> findAll() {
		final Collection<SubActor> res = this.subActorRepository.findAll();
		return res;
	}

	public SubActor save(final SubActor subActor) {
		Assert.notNull(subActor);
		Assert.isTrue(this.actorService.isUser() || this.actorService.isVet() || this.actorService.isCompany());
		SubActor saved;
		saved = this.subActorRepository.save(subActor);
		return saved;
	}

	// Other methods -------------------------------------
	public String encodePass(String password) {
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		return password;
	}

	//Devuelve si es mayor de edad
	public boolean esMayorDeEdad(final Date date) {
		boolean res = false;

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -18);
		if (date.before(calendar.getTime()))
			res = true;

		return res;
	}
}
