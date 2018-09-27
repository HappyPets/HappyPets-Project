
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.Authority;
import security.UserAccount;
import domain.Advertisement;
import domain.Comment;
import domain.Company;
import domain.Donation;
import domain.Valoration;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository	companyRepository;

	@Autowired
	private ActorService		actorService;


	// Constructor --------------------------------------------
	public CompanyService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Company create() {
		final Company res = new Company();
		res.setAdvertisements(new ArrayList<Advertisement>());
		res.setComments(new ArrayList<Comment>());
		res.setDonations(new ArrayList<Donation>());
		res.setValorationsReceived(new ArrayList<Valoration>());
		final UserAccount useracc = new UserAccount();
		final Authority auth = new Authority();
		auth.setAuthority(Authority.COMPANY);
		useracc.addAuthority(auth);
		res.setUserAccount(useracc);
		return res;
	}

	public Company saveNewCompany(final Company company) {
		Company saved;
		Assert.notNull(company);
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String password = company.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		company.getUserAccount().setPassword(password);
		saved = this.companyRepository.save(company);
		return saved;
	}

	public Company save(final Company company) {
		Assert.notNull(company);
		Assert.isTrue(this.actorService.isCompany());
		Company saved;
		saved = this.companyRepository.save(company);
		return saved;
	}

	public Company findOne(final int companyId) {
		final Company res = this.companyRepository.findOne(companyId);
		return res;
	}

	public Collection<Company> findAll() {
		final Collection<Company> res = this.companyRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Queries -------------------------------------------
	//Media de anuncios por empresa.
	public Double avgAvertisementsPerCompany() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.companyRepository.avgAvertisementsPerCompany();
	}

}
