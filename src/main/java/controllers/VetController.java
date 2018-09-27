
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SubActorService;
import services.VetService;
import domain.Vet;
import forms.VetForm;

@Controller
@RequestMapping("/vet")
public class VetController extends AbstractController {

	@Autowired
	private VetService		vetService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private SubActorService	subActorService;


	//Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(final int edit) {
		ModelAndView res;
		VetForm vetForm = new VetForm();
		if (edit == 1)
			try {
				final Vet vet = (Vet) this.actorService.findByPrincipal();
				vetForm = new VetForm(vet);
				res = this.createEditModelAndView(vetForm);
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
			}
		else
			res = this.createEditModelAndView(vetForm);

		return res;
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final VetForm vetForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(vetForm);
		else if (vetForm.getPassword().equals(vetForm.getRepeatPassword()))
			if (vetForm.getAcceptLOPD())
				if (this.subActorService.esMayorDeEdad(vetForm.getBirthDate()))
					try {
						Vet vet = this.vetService.create();
						if (vetForm.getId() != 0)
							vet = this.vetService.findOne(vetForm.getId());

						vet.setName(vetForm.getName());
						vet.setSurname(vetForm.getSurname());
						vet.setDNI(vetForm.getDNI());
						vet.setBirthDate(vetForm.getBirthDate());
						vet.setEmail(vetForm.getEmail());
						vet.setPhone(vetForm.getPhone());
						vet.setAddress(vetForm.getAddress());
						vet.setCity(vetForm.getCity());
						vet.setLicenseNumber(vetForm.getLicenseNumber());
						vet.getUserAccount().setUsername(vetForm.getUsername());
						vet.getUserAccount().setPassword(vetForm.getPassword());

						vet = this.vetService.saveNewVet(vet);
						res = new ModelAndView("redirect:/welcome/index.do");

						//res = new ModelAndView("redirect:/profile/subActor/display.do?subActorId=" + vet.getId());
					} catch (final Throwable oops) {
						res = this.createEditModelAndView(vetForm, "vet.commit.error");
					}

				else
					res = this.createEditModelAndView(vetForm, "user.menorEdad");
			else {
				res.addObject("LOPDerror", 1);
				res = this.createEditModelAndView(vetForm, "vet.disagree");
			}
		else {
			res.addObject("passError", 1);
			res = this.createEditModelAndView(vetForm, "vet.password.error");
		}
		return res;
	}

	//other methods
	protected ModelAndView createEditModelAndView(final VetForm vetForm) {
		assert vetForm != null;
		final ModelAndView res = this.createEditModelAndView(vetForm, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final VetForm vetForm, final String message) {
		assert vetForm != null;
		ModelAndView res;
		res = new ModelAndView("vet/register");
		res.addObject("vetForm", vetForm);
		res.addObject("formAction", "vet/register.do");
		res.addObject("message", message);
		return res;
	}
}
