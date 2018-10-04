
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdoptionRequestService;
import controllers.AbstractController;
import domain.AdoptionRequest;
import domain.User;

@Controller
@RequestMapping("/adoptionRequest/user")
public class AdoptionRequestUserController extends AbstractController {

	@Autowired
	private AdoptionRequestService	adoptionRequestService;

	@Autowired
	private ActorService			actorService;


	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("adoptionRequest/list");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<AdoptionRequest> adoptionRequests = logued.getAdoptionRequests();
			res.addObject("adoptionRequests", adoptionRequests);
			res.addObject("error", "");
		} catch (final Throwable oops) {
			res.addObject("error", "adoptionRequest.no.results");
		}
		return res;
	}

	// Adopt pet ----------------------------------------------------------
	@RequestMapping(value = "/adopt", method = RequestMethod.GET)
	public ModelAndView adopt(@RequestParam final int petId) {
		ModelAndView res;
		try {
			this.adoptionRequestService.requestAdoption(petId);
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&adopt=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&adopt=false");
		}

		return res;
	}

	// Cancel adoption request --------------------------------------------
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int petId) {
		ModelAndView res;
		try {
			int adoptionRequestId = 0;
			final User logued = (User) this.actorService.findByPrincipal();
			final Collection<AdoptionRequest> adoptionRequests = logued.getAdoptionRequests();
			for (final AdoptionRequest adoptionRequest : adoptionRequests)
				if (adoptionRequest.getPet().getId() == petId)
					adoptionRequestId = adoptionRequest.getId();
			this.adoptionRequestService.cancelAdoptionRequest(adoptionRequestId);
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&cancel=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&cancel=false");
		}

		return res;
	}

	// Accept adoption request --------------------------------------------
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int adoptionRequestId, @RequestParam final int petId) {
		ModelAndView res;
		try {
			this.adoptionRequestService.acceptAdoptionRequest(adoptionRequestId);
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&accept=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&accept=false");
		}

		return res;
	}

	// Deny adoption request --------------------------------------------
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam final int adoptionRequestId, @RequestParam final int petId) {
		ModelAndView res;
		try {
			this.adoptionRequestService.denyAdoptionRequest(adoptionRequestId);
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&deny=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/pet/user/display.do?petId=" + petId + "&deny=false");
		}

		return res;
	}

}
