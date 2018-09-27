
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;


	// Constructor --------------------------------------------
	public ActorService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Actor findOne(final int actorId) {
		final Actor res = this.actorRepository.findOne(actorId);
		return res;
	}

	public Collection<Actor> findAll() {
		final Collection<Actor> res = this.actorRepository.findAll();
		return res;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(this.isAuthenticated());
		Actor saved;
		saved = this.actorRepository.save(actor);
		return saved;
	}

	// Other methods -------------------------------------
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = this.actorRepository.findByPrincipal(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public boolean checkRole(final String role) {
		boolean result;
		Collection<Authority> authorities;

		result = false;
		authorities = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authorities)
			result = result || a.getAuthority().equals(role);

		return result;
	}

	public boolean checkActorRole(final String role, final Actor actor) {
		boolean result;
		Collection<Authority> authorities;

		result = false;
		authorities = actor.getUserAccount().getAuthorities();
		for (final Authority a : authorities)
			result = result || a.getAuthority().equals(role);

		return result;
	}

	public boolean isAuthenticated() {
		return LoginService.getPrincipal() != null;
	}

	public boolean isUnauthenticated() {
		SecurityContext context;
		Authentication authentication;
		context = SecurityContextHolder.getContext();
		Assert.notNull(context);
		authentication = context.getAuthentication();
		return authentication == null;
	}

	public boolean isAdmin() {
		boolean result;
		result = this.checkRole(Authority.ADMINISTRATOR);
		return result;
	}

	public boolean isUser() {
		boolean result;
		result = this.checkRole(Authority.USER);
		return result;
	}

	public boolean isCompany() {
		boolean result;
		result = this.checkRole(Authority.COMPANY);
		return result;
	}

	public boolean isVet() {
		boolean result;
		result = this.checkRole(Authority.VET);
		return result;
	}
}
