
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PriceAdvertisementService;
import controllers.AbstractController;
import domain.PriceAdvertisement;
import forms.PriceAdvertisementForm;

@Controller
@RequestMapping("/priceAdvertisement/administrator")
public class PriceAdvertisementAdministratorController extends AbstractController {

	@Autowired
	private PriceAdvertisementService	priceAdvertisementService;


	// Create ----------------------------------------------------------
	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView res = new ModelAndView();
		try {
			final PriceAdvertisement priceAdvertisement = this.priceAdvertisementService.getPriceAdvertisement();
			final PriceAdvertisementForm priceAdvertisementForm = new PriceAdvertisementForm(priceAdvertisement);
			res = this.createEditModelAndView(priceAdvertisementForm);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
		}
		return res;
	}
	// Save ------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PriceAdvertisementForm priceAdvertisementForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(priceAdvertisementForm);
		else
			try {
				PriceAdvertisement priceAdvertisement = this.priceAdvertisementService.findOne(priceAdvertisementForm.getId());

				priceAdvertisement.setValue(priceAdvertisementForm.getValue());

				priceAdvertisement = this.priceAdvertisementService.save(priceAdvertisement);

				res = new ModelAndView("redirect:edit.do?id=" + priceAdvertisement.getId());
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(priceAdvertisementForm, "error.save.priceAdvertisement");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final PriceAdvertisementForm priceAdvertisementForm) {
		ModelAndView result;
		result = this.createEditModelAndView(priceAdvertisementForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PriceAdvertisementForm priceAdvertisementForm, final String message) {
		final ModelAndView res = new ModelAndView("priceAdvertisement/edit");
		res.addObject("priceAdvertisementForm", priceAdvertisementForm);
		res.addObject("formAction", "priceAdvertisement/administrator/edit.do");
		res.addObject("error", message);
		return res;
	}
}
