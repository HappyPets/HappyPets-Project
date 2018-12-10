
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CauseService;
import controllers.AbstractController;
import domain.Cause;
import domain.Donation;
import domain.Priority;
import forms.CauseForm;

@Controller
@RequestMapping("/cause/administrator")
public class CauseAdministratorController extends AbstractController {

	@Autowired
	private CauseService	causeService;


	//	@Autowired
	//	private ActorService	actorService;

	// Display -------------------------------
	@RequestMapping(value = "/display")
	public ModelAndView display(@RequestParam final int causeId) {
		ModelAndView res = new ModelAndView();
		try {
			res = new ModelAndView("cause/displayAdmin");
			final Cause cause = this.causeService.findOne(causeId);
			Double donations = 0.0;
			for (final Donation d : cause.getDonations())
				donations += d.getQuantity();
			res.addObject("cause", cause);
			res.addObject("donations", donations);
			res.addObject("canCancel", cause.getIsActive());

		} catch (final Throwable oops) {
			res.addObject("error", "Error");
		}
		return res;
	}

	// Create ----------------------------------------------------------

	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final int causeId) {
		ModelAndView res = new ModelAndView();
		CauseForm causeForm = new CauseForm();
		if (causeId != 0)
			try {
				final Cause cause = this.causeService.findOne(causeId);
				causeForm = new CauseForm(cause);
				Assert.isTrue(cause.getDonations().size() == 0);
				res = this.createEditModelAndView(causeForm);
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/cause/administrator/display.do?causeId=" + causeId);
			}
		else
			res = this.createEditModelAndView(causeForm);

		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CauseForm causeForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(causeForm);
		else
			try {
				Cause cause;
				if (causeForm.getId() == 0)
					cause = this.causeService.create();
				else
					cause = this.causeService.findOne(causeForm.getId());

				cause.setTitle(causeForm.getTitle());
				cause.setDescription(causeForm.getDescription());
				cause.setPicture(causeForm.getPicture());
				cause.setPriority(causeForm.getPriority());
				cause = this.causeService.save(cause);

				res = new ModelAndView("redirect:/cause/subActor/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(causeForm, "mService.error.save");
			}
		return res;
	}

	// Deactivate or cancel -------------------------------------------------------------
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int causeId) {
		ModelAndView res;
		try {
			final Cause cause = this.causeService.findOne(causeId);
			this.causeService.deactiveCause(cause);
			res = new ModelAndView("redirect:/cause/subActor/list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/cause/administrator/display.do?causeId=" + causeId);
		}

		return res;
	}
	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final CauseForm causeForm) {
		ModelAndView result;
		result = this.createEditModelAndView(causeForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CauseForm causeForm, final String message) {
		final ModelAndView res = new ModelAndView("cause/create");
		res.addObject("causeForm", causeForm);
		res.addObject("actionForm", "cause/administrator/create.do");
		res.addObject("priorities", Priority.values());
		res.addObject("message", message);
		res.addObject("error", "");
		return res;
	}

}
