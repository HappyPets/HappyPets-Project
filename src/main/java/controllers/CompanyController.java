
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CompanyService;
import services.SubActorService;
import domain.Company;
import forms.CompanyForm;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	@Autowired
	private CompanyService	companyService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private SubActorService	subActorService;


	//Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(final int edit) {
		ModelAndView res;
		CompanyForm companyForm = new CompanyForm();
		if (edit == 1)
			try {
				final Company company = (Company) this.actorService.findByPrincipal();
				companyForm = new CompanyForm(company);
				res = this.createEditModelAndView(companyForm);
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
			}
		else
			res = this.createEditModelAndView(companyForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CompanyForm companyForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(companyForm);
		else if (companyForm.getPassword().equals(companyForm.getRepeatPassword()))
			if (companyForm.getAcceptLOPD())
				if (this.subActorService.esMayorDeEdad(companyForm.getBirthDate()))
					try {
						Company company = this.companyService.create();
						if (companyForm.getId() != 0)
							company = this.companyService.findOne(companyForm.getId());

						company.setName(companyForm.getName());
						company.setSurname(companyForm.getSurname());
						company.setDNI(companyForm.getDNI());
						company.setBirthDate(companyForm.getBirthDate());
						company.setEmail(companyForm.getEmail());
						company.setPhone(companyForm.getPhone());
						company.setAddress(companyForm.getAddress());
						company.setCity(companyForm.getCity());
						company.setBusinessName(companyForm.getBusinessName());
						company.setVAT(companyForm.getVAT());
						company.getUserAccount().setUsername(companyForm.getUsername());
						company.getUserAccount().setPassword(companyForm.getPassword());

						company = this.companyService.saveNewCompany(company);
						res = new ModelAndView("redirect:/welcome/index.do");

						//res = new ModelAndView("redirect:/profile/subActor/display.do?subActorId=" + company.getId());
					} catch (final Throwable oops) {
						res = this.createEditModelAndView(companyForm, "company.commit.error");
					}
				else
					res = this.createEditModelAndView(companyForm, "user.menorEdad");
			else {
				res.addObject("LOPDerror", 1);
				res = this.createEditModelAndView(companyForm, "company.disagree");
			}
		else {
			res.addObject("passError", 1);
			res = this.createEditModelAndView(companyForm, "company.password.error");
		}
		return res;
	}
	//other methods
	protected ModelAndView createEditModelAndView(final CompanyForm companyForm) {
		assert companyForm != null;
		final ModelAndView res = this.createEditModelAndView(companyForm, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final CompanyForm companyForm, final String message) {
		assert companyForm != null;
		ModelAndView res;
		res = new ModelAndView("company/register");
		res.addObject("companyForm", companyForm);
		res.addObject("formAction", "company/register.do");
		res.addObject("message", message);
		return res;
	}
}
