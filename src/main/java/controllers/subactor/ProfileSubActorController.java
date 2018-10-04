
package controllers.subactor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.SubActorService;
import services.ValorationService;
import controllers.AbstractController;
import domain.SubActor;

@Controller
@RequestMapping("/profile/subActor")
public class ProfileSubActorController extends AbstractController {

	@Autowired
	private SubActorService		subActorService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ValorationService	valorationService;


	// List ---------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res = new ModelAndView("subActor/list");
		try {
			final Collection<SubActor> subActors = this.subActorService.findAll();
			final SubActor logued = (SubActor) this.actorService.findByPrincipal();
			subActors.remove(logued);
			res.addObject("subActors", subActors);
		} catch (final Throwable oops) {
			res.addObject("error", "error.list");
		}
		return res;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int subActorId, @RequestParam(required = false) final String valoratedOK) {
		final ModelAndView res = new ModelAndView("subActor/profile");
		try {
			SubActor subActor = (SubActor) this.actorService.findByPrincipal();
			final SubActor logued = (SubActor) this.actorService.findByPrincipal();
			boolean valorado = false;
			boolean own = false;
			// Comprobamos si el SubActor que se va a mostrar es el autenticado o no.
			// También comprobamos si el actor autenticado puede valorar al SubActor que se va a mostrar o no.
			if (subActorId != 0) {
				subActor = this.subActorService.findOne(subActorId);
				if (subActor.equals(logued)) {
					valorado = true;
					own = true;
				} else
					valorado = !this.valorationService.canValorate(subActorId);
			} else {
				valorado = true;
				own = true;
			}
			// Comprobamos de qué tipo es SubActor que se va a mostrar.
			String type = "";
			if (this.actorService.checkActorRole(Authority.USER, subActor))
				type = "U";
			else if (this.actorService.checkActorRole(Authority.VET, subActor))
				type = "V";
			else if (this.actorService.checkActorRole(Authority.COMPANY, subActor))
				type = "C";
			// Comprobamos de qué tipo es el actor autenticado.
			final boolean isUser = this.actorService.isUser();
			final boolean isVet = this.actorService.isVet();
			final boolean isCompany = this.actorService.isCompany();
			// Agregamos todos los valores a la vista.
			res.addObject("subActor", subActor);
			res.addObject("valoration", this.valorationService.valorationMedia(subActor.getId()));
			res.addObject("type", type);
			res.addObject("isUser", isUser);
			res.addObject("isVet", isVet);
			res.addObject("isCompany", isCompany);
			res.addObject("own", own);
			res.addObject("valorado", valorado);
			res.addObject("valoratedOK", valoratedOK);
		} catch (final Throwable oops) {
			res.addObject("error", "error.display");
			res.addObject("valoratedOK", valoratedOK);
		}
		return res;
	}

}
