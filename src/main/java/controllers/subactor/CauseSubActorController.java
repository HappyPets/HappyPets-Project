
package controllers.subactor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CauseService;
import controllers.AbstractController;
import domain.Cause;

@Controller
@RequestMapping("/cause/subActor")
public class CauseSubActorController extends AbstractController {

	@Autowired
	private CauseService	causeService;

	@Autowired
	private ActorService	actorService;


	// List -------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required = false) final String create) {
		ModelAndView res = new ModelAndView();
		try {
			res = new ModelAndView("cause/list");
			final Collection<Cause> causes = this.causeService.getActiveCauses();
			if (this.actorService.isAuthenticated() && this.actorService.isAdmin()) {
				final Collection<Cause> cancelCauses = this.causeService.getDeactiveCauses();
				res.addObject("cancelCauses", cancelCauses);
			}
			res.addObject("causes", causes);
			res.addObject("error", "");
			res.addObject("create", create);

		} catch (final Throwable oops) {
			res.addObject("error", "error.list");
		}
		return res;
	}

	// Display -------------------------------
	@RequestMapping(value = "/display")
	public ModelAndView display(@RequestParam final int causeId) {
		ModelAndView res = new ModelAndView();
		try {
			res = new ModelAndView("cause/display");
			final Cause cause = this.causeService.findOne(causeId);
			res.addObject("cause", cause);
		} catch (final Throwable oops) {
			res.addObject("error", "error.display");
		}
		return res;
	}
}
