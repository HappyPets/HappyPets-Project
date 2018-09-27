
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
import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService		userService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private SubActorService	subActorService;


	//Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(final int edit) {
		ModelAndView res;
		UserForm userForm = new UserForm();
		if (edit == 1)
			try {
				final User user = (User) this.actorService.findByPrincipal();
				userForm = new UserForm(user);
				res = this.createEditModelAndView(userForm);
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
			}
		else
			res = this.createEditModelAndView(userForm);
		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final UserForm userForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(userForm);
		else if (userForm.getPassword().equals(userForm.getRepeatPassword()))
			if (userForm.getAcceptLOPD())
				if (this.subActorService.esMayorDeEdad(userForm.getBirthDate()))
					try {
						User user = this.userService.create();
						if (userForm.getId() != 0)
							user = this.userService.findOne(userForm.getId());

						user.setName(userForm.getName());
						user.setSurname(userForm.getSurname());
						user.setDNI(userForm.getDNI());
						user.setBirthDate(userForm.getBirthDate());
						user.setEmail(userForm.getEmail());
						user.setPhone(userForm.getPhone());
						user.setAddress(userForm.getAddress());
						user.setCity(userForm.getCity());
						user.setBiography(userForm.getBiography());
						user.setPicture(userForm.getPicture());
						user.getUserAccount().setUsername(userForm.getUsername());
						user.getUserAccount().setPassword(userForm.getPassword());

						user = this.userService.saveNewUser(user);
						res = new ModelAndView("redirect:/welcome/index.do");

						//res = new ModelAndView("redirect:/profile/subActor/display.do?subActorId=" + user.getId());
					} catch (final Throwable oops) {
						res = this.createEditModelAndView(userForm, "user.commit.error");
					}
				else
					res = this.createEditModelAndView(userForm, "user.menorEdad");
			else {
				res.addObject("LOPDerror", 1);
				res = this.createEditModelAndView(userForm, "user.disagree");
			}
		else {
			res.addObject("passError", 1);
			res = this.createEditModelAndView(userForm, "user.password.error");
		}
		return res;
	}
	//other methods
	protected ModelAndView createEditModelAndView(final UserForm userForm) {
		assert userForm != null;
		final ModelAndView res = this.createEditModelAndView(userForm, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final UserForm userForm, final String message) {
		assert userForm != null;
		ModelAndView res;
		res = new ModelAndView("user/register");
		res.addObject("userForm", userForm);
		res.addObject("formAction", "user/register.do");
		res.addObject("message", message);
		return res;
	}

}
