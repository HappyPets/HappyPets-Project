
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InscriptionService;
import controllers.AbstractController;
import domain.Inscription;
import domain.User;

@Controller
@RequestMapping("/inscription/user")
public class InscriptionUserController extends AbstractController {

	@Autowired
	private InscriptionService	inscriptionService;

	@Autowired
	private ActorService		actorService;


	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("inscription/list");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<Inscription> inscriptions = logued.getInscriptions();
			res.addObject("inscriptions", inscriptions);
			res.addObject("error", "");
		} catch (final Throwable oops) {
			res.addObject("error", "inscription.error.list");
		}
		return res;
	}

	// Send inscription ---------------------------------------------------
	@RequestMapping(value = "/sendInscription", method = RequestMethod.GET)
	public ModelAndView sendInscription(@RequestParam final int jobOfferId) {
		ModelAndView res;
		try {
			this.inscriptionService.sendInscription(jobOfferId);
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + jobOfferId + "&inscription=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + jobOfferId + "&inscription=false");
		}

		return res;
	}

	// Cancel inscription -------------------------------------------------
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int inscriptionId) {
		ModelAndView res;
		final Inscription inscription = this.inscriptionService.findOne(inscriptionId);
		try {
			this.inscriptionService.cancelInscription(inscriptionId);
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionCancel=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionCancel=false");
		}

		return res;
	}

	// Accept adoption request --------------------------------------------
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int inscriptionId) {
		ModelAndView res;
		final Inscription inscription = this.inscriptionService.findOne(inscriptionId);
		try {
			this.inscriptionService.acceptInscription(inscriptionId);
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionAccept=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionAccept=false");
		}

		return res;
	}

	// Deny adoption request --------------------------------------------
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam final int inscriptionId) {
		ModelAndView res;
		final Inscription inscription = this.inscriptionService.findOne(inscriptionId);
		try {
			this.inscriptionService.denyInscription(inscriptionId);
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionDeny=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/jobOffer/user/display.do?jobOfferId=" + inscription.getJobOffer().getId() + "&inscriptionDeny=false");
		}

		return res;
	}

}
