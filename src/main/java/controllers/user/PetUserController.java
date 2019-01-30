/*
 * ProfileController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdoptionRequestService;
import services.CategoryService;
import services.PetService;
import controllers.AbstractController;
import domain.AdoptionRequest;
import domain.Genre;
import domain.Pet;
import domain.Status;
import domain.TypeAge;
import domain.User;
import forms.PetForm;

@Controller
@RequestMapping("/pet/user")
public class PetUserController extends AbstractController {

	@Autowired
	private PetService				petService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CategoryService			categoryService;

	@Autowired
	private AdoptionRequestService	adoptionRequestService;


	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String delete) {
		final ModelAndView res = new ModelAndView("pet/list");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			res.addObject("pets", logued.getPets());
			res.addObject("owns", true);
			res.addObject("error", "");
			res.addObject("delete", delete);
		} catch (final Throwable oops) {
			res.addObject("error", "error.list");
		}
		return res;
	}

	// Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int petId, @RequestParam(required = false) final String delete, @RequestParam(required = false) final String create, @RequestParam(required = false) final String edit,
		@RequestParam(required = false) final String adopt, @RequestParam(required = false) final String cancel, @RequestParam(required = false) final String accept, @RequestParam(required = false) final String deny) {
		final ModelAndView res = new ModelAndView("pet/display");
		boolean adopted = false;
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Pet pet = this.petService.findOne(petId);
			res.addObject("pet", pet);
			if (pet.getOwner().equals(logued))
				res.addObject("own", true);
			else {
				final Collection<AdoptionRequest> loguedAdoptionRequests = logued.getAdoptionRequests();
				for (final AdoptionRequest adoptionRequest : loguedAdoptionRequests)
					if (adoptionRequest.getPet().equals(pet) && adoptionRequest.getStatus().equals(Status.PENDING)) {
						adopted = true;
						break;
					}
			}
			res.addObject("error", "");
			res.addObject("delete", delete);
			res.addObject("create", create);
			res.addObject("edit", edit);
			res.addObject("adopt", adopt);
			res.addObject("adopted", adopted);
			res.addObject("cancel", cancel);
			res.addObject("accept", accept);
			res.addObject("deny", deny);
		} catch (final Throwable oops) {
			res.addObject("error", "error.display");
			res.addObject("adopted", adopted);
		}

		return res;
	}

	// Delete -------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int petId) {
		ModelAndView res;
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Pet pet = this.petService.findOne(petId);
			Assert.isTrue(pet.getOwner().equals(logued));
			this.petService.delete(petId);
			res = new ModelAndView("redirect:/pet/user/list.do?delete=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&delete=true");
		}

		return res;
	}

	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final int petId, @RequestParam(required = false) final String create) {
		ModelAndView res = new ModelAndView();
		PetForm petForm = new PetForm();
		if (petId != 0)
			try {
				final Pet pet = this.petService.findOne(petId);
				Assert.isTrue(this.actorService.isUser());
				final User logued = (User) this.actorService.findByPrincipal();
				Assert.isTrue(logued.getPets().contains(pet));
				petForm = new PetForm(pet);
				res = this.createEditModelAndView(petForm);
				res.addObject("create", create);
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
			}
		else {
			res = this.createEditModelAndView(petForm);
			res.addObject("create", create);
		}
		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PetForm petForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(petForm);
		else
			try {
				boolean oldAdoptionValue = false;
				Pet pet = this.petService.create();
				if (petForm.getId() != 0) {
					pet = this.petService.findOne(petForm.getId());
					oldAdoptionValue = pet.isInAdoption();
				}
				Assert.isTrue((this.petService.hasTabooWords(petForm)) == false);

				pet.setCategory(petForm.getCategory());
				pet.setName(petForm.getName());
				pet.setAge(petForm.getAge());
				pet.setTypeAge(petForm.getTypeAge());
				pet.setGenre(petForm.getGenre());
				pet.setDescription(petForm.getDescription());
				pet.setHealthDescription(petForm.getHealthDescription());
				pet.setCity(petForm.getCity());
				pet.setPicture(petForm.getPicture());
				pet.setWeight(petForm.getWeight());
				pet.setHeight(petForm.getHeight());
				pet.setInAdoption(petForm.isInAdoption());

				pet = this.petService.save(pet);

				if (oldAdoptionValue != petForm.isInAdoption() && oldAdoptionValue)
					this.adoptionRequestService.denyAllAdoptionRequests(pet.getId());

				if (petForm.getId() != 0)
					//					res = new ModelAndView("redirect:/pet/user/display.do?petId=" + pet.getId() + "&edit=true");
					res = new ModelAndView("redirect:/pet/user/list.do");
				else
					//					res = new ModelAndView("redirect:/pet/user/display.do?petId=" + pet.getId() + "&create=true");
					res = new ModelAndView("redirect:/pet/user/list.do");
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/pet/user/create.do?petId=" + petForm.getId() + "&create=false");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final PetForm petForm) {
		ModelAndView result;
		result = this.createEditModelAndView(petForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PetForm petForm, final String message) {
		final ModelAndView res = new ModelAndView("pet/create");
		res.addObject("petForm", petForm);
		res.addObject("formAction", "pet/user/create.do");
		res.addObject("categories", this.categoryService.findAll());
		res.addObject("genres", Genre.values());
		res.addObject("typeAges", TypeAge.values());
		res.addObject("error", "");
		return res;
	}

}
