
package controllers.user;

import java.util.Calendar;
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
import services.JobOfferService;
import controllers.AbstractController;
import domain.Inscription;
import domain.JobOffer;
import domain.Status;
import domain.User;
import forms.JobOfferForm;

@Controller
@RequestMapping("/jobOffer/user")
public class JobOfferUserController extends AbstractController {

	@Autowired
	private JobOfferService	jobOfferService;

	@Autowired
	private ActorService	actorService;


	// Own ----------------------------------------------------------------
	@RequestMapping(value = "/own", method = RequestMethod.GET)
	public ModelAndView own(@RequestParam(required = false) final String delete) {
		final ModelAndView res = new ModelAndView("jobOffer/list");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<JobOffer> jobOffers = logued.getJobOffers();
			res.addObject("jobOffers", jobOffers);
			res.addObject("error", "");
			res.addObject("own", true);
			res.addObject("delete", delete);
		} catch (final Throwable oops) {
			res.addObject("error", "jobOffer.error.list");
			res.addObject("delete", delete);
			res.addObject("own", true);
		}
		return res;
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("jobOffer/list");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<JobOffer> jobOffers = this.jobOfferService.findAll();
			jobOffers.removeAll(logued.getJobOffers());
			jobOffers.removeAll(this.jobOfferService.getBannedJobOffers());
			res.addObject("jobOffers", jobOffers);
			res.addObject("error", "");
			res.addObject("own", false);
		} catch (final Throwable oops) {
			res.addObject("error", "jobOffer.error.list");
		}
		return res;
	}

	// Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int jobOfferId, @RequestParam(required = false) final String inscriptions, @RequestParam(required = false) final String edit, @RequestParam(required = false) final String create, @RequestParam(
		required = false) final String inscription, @RequestParam(required = false) final String inscriptionCancel, @RequestParam(required = false) final String inscriptionAccept, @RequestParam(required = false) final String inscriptionDeny) {
		final ModelAndView res = new ModelAndView("jobOffer/display");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final JobOffer jobOffer = this.jobOfferService.findOne(jobOfferId);
			boolean own = false;
			boolean inscripted = false;
			if (jobOffer.getOwner().equals(logued))
				own = true;
			else {
				final Collection<Inscription> allInscriptions = logued.getInscriptions();
				for (final Inscription inscriptionAux : allInscriptions)
					if (inscriptionAux.getJobOffer().equals(jobOffer) && !inscriptionAux.getStatus().equals(Status.CANCELLED)) {
						inscripted = true;
						break;
					}
			}
			res.addObject("jobOffer", jobOffer);
			res.addObject("error", "");
			res.addObject("own", own);
			res.addObject("inscripted", inscripted);
			res.addObject("inscriptions", inscriptions);
			res.addObject("edit", edit);
			res.addObject("create", create);
			res.addObject("inscription", inscription);
			res.addObject("inscriptionCancel", inscriptionCancel);
			res.addObject("inscriptionAccept", inscriptionAccept);
			res.addObject("inscriptionDeny", inscriptionDeny);
		} catch (final Throwable oops) {
			res.addObject("error", "jobOffer.error.display");
			res.addObject("inscriptions", inscriptions);
			res.addObject("edit", edit);
			res.addObject("create", create);
			res.addObject("inscription", inscription);
			res.addObject("inscriptionCancel", inscriptionCancel);
			res.addObject("inscriptionAccept", inscriptionAccept);
			res.addObject("inscriptionDeny", inscriptionDeny);
		}
		return res;
	}

	// Delete -------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int jobOfferId) {
		ModelAndView res;
		try {
			this.jobOfferService.delete(jobOfferId);
			res = new ModelAndView("redirect:/jobOffer/user/own.do?delete=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/jobOffer/user/own.do?delete=false");
		}

		return res;
	}

	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final int jobOfferId, @RequestParam(required = false) final String create) {
		ModelAndView res = new ModelAndView();
		JobOfferForm jobOfferForm = new JobOfferForm();
		if (jobOfferId != 0)
			try {
				final JobOffer jobOffer = this.jobOfferService.findOne(jobOfferId);
				final User logued = (User) this.actorService.findByPrincipal();
				Assert.isTrue(jobOffer.getOwner().equals(logued));
				if (jobOffer.getInscriptions().size() > 0)
					res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + jobOffer.getId() + "&inscriptions=true");
				else {
					jobOfferForm = new JobOfferForm(jobOffer);
					res = this.createEditModelAndView(jobOfferForm);
					res.addObject("create", create);
				}
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
				res.addObject("create", create);
			}
		else
			res = this.createEditModelAndView(jobOfferForm);
		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final JobOfferForm jobOfferForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();

		if (binding.hasErrors())
			res = this.createEditModelAndView(jobOfferForm);
		else
			try {
				JobOffer jobOffer = this.jobOfferService.create();
				if (jobOfferForm.getId() != 0)
					jobOffer = this.jobOfferService.findOne(jobOfferForm.getId());

				Assert.isTrue((this.jobOfferService.hasTabooWords(jobOfferForm)) == false);

				Assert.isTrue(jobOfferForm.getStartDate().after(Calendar.getInstance().getTime()));
				Assert.isTrue(jobOfferForm.getEndDate().after(jobOfferForm.getStartDate()));
				jobOffer.setTitle(jobOfferForm.getTitle());
				jobOffer.setDescription(jobOfferForm.getDescription());
				jobOffer.setCity(jobOfferForm.getCity());
				jobOffer.setStartDate(jobOfferForm.getStartDate());
				jobOffer.setEndDate(jobOfferForm.getEndDate());
				jobOffer.setSalary(jobOfferForm.getSalary());
				jobOffer.setPet(jobOfferForm.getPet());

				jobOffer = this.jobOfferService.save(jobOffer);

				if (jobOfferForm.getId() != 0)
					res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + jobOffer.getId() + "&edit=true");
				else
					res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + jobOffer.getId() + "&create=true");
			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/jobOffer/user/create.do?jobOfferId=" + jobOfferForm.getId() + "&create=false");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final JobOfferForm jobOfferForm) {
		ModelAndView result;
		result = this.createEditModelAndView(jobOfferForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final JobOfferForm jobOfferForm, final String message) {
		final ModelAndView res = new ModelAndView("jobOffer/create");
		final User logued = (User) this.actorService.findByPrincipal();
		res.addObject("jobOfferForm", jobOfferForm);
		res.addObject("formAction", "jobOffer/user/create.do");
		res.addObject("pets", logued.getPets());
		res.addObject("error", "");
		return res;
	}

}
