
package controllers.subactor;

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
import services.AdvertisementService;
import services.CreditCardService;
import services.PriceAdvertisementService;
import controllers.AbstractController;
import domain.Advertisement;
import domain.CreditCard;
import domain.PriceAdvertisement;
import domain.SubActor;
import forms.AdvertisementForm;

@Controller
@RequestMapping("/advertisement/subActor")
public class AdvertisementSubActorController extends AbstractController {

	@Autowired
	private AdvertisementService		advertisementService;

	@Autowired
	private CreditCardService			creditCardService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private PriceAdvertisementService	priceAdvertisementService;


	// List own-------------------------------
	@RequestMapping(value = "/listOwn")
	public ModelAndView listOwn(@RequestParam(required = false, defaultValue = "") final String delete) {
		ModelAndView res = new ModelAndView();
		try {
			Assert.isTrue(this.actorService.isAuthenticated());
			final SubActor subActor = (SubActor) this.actorService.findByPrincipal();
			res = new ModelAndView("advertisement/listOwn");
			final Collection<Advertisement> advertisements = subActor.getAdvertisements();
			res.addObject("advertisements", advertisements);
			if (delete.equals("true"))
				res.addObject("error", "error.advertisement.delete");

		} catch (final Throwable oops) {
			res.addObject("error", "error.list.advertisement");
		}
		return res;
	}

	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final int id) {
		ModelAndView res = new ModelAndView();
		try {
			AdvertisementForm advertisementForm = new AdvertisementForm();
			if (id != 0) {
				final Advertisement advertisement = this.advertisementService.findOne(id);
				advertisementForm = new AdvertisementForm(advertisement);
				Assert.isTrue(this.actorService.findByPrincipal().getId() == advertisement.getSubActor().getId());
				Assert.isTrue(!advertisement.getIsBanned());
			}
			res = this.createEditModelAndView(advertisementForm);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
		}
		return res;
	}
	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AdvertisementForm advertisementForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(advertisementForm);
		else
			try {
				Advertisement advertisement = this.advertisementService.create();
				if (advertisementForm.getId() != 0)
					advertisement = this.advertisementService.findOne(advertisementForm.getId());

				//Comprobamos que la creditcard introducida es válida
				final CreditCard creditCard = new CreditCard();
				creditCard.setHolderName(advertisementForm.getHolderName());
				creditCard.setBrandName(advertisementForm.getBrandName());
				creditCard.setNumber(advertisementForm.getNumber());
				creditCard.setExpirationMonth(advertisementForm.getExpirationMonth());
				creditCard.setExpirationYear(advertisementForm.getExpirationYear());
				creditCard.setCvv(advertisementForm.getCvv());

				Assert.isTrue(this.creditCardService.isValidCreditCard2(creditCard));

				advertisement.setCreditCard(creditCard);
				advertisement.setBanner(advertisementForm.getBanner());
				advertisement.setTargetPage(advertisementForm.getTargetPage());

				advertisement = this.advertisementService.save(advertisement);

				res = new ModelAndView("redirect:/advertisement/subActor/listOwn.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(advertisementForm, "error.save.advertisement");
			}
		return res;
	}

	// Delete -------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int id) {
		ModelAndView res;
		try {
			final SubActor subActor = (SubActor) this.actorService.findByPrincipal();
			final Advertisement advertisement = this.advertisementService.findOne(id);
			Assert.isTrue(advertisement.getSubActor().equals(subActor));
			this.advertisementService.delete(advertisement);
			res = new ModelAndView("redirect:/advertisement/subActor/listOwn.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
		}

		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final AdvertisementForm advertisementForm) {
		ModelAndView result;
		result = this.createEditModelAndView(advertisementForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AdvertisementForm advertisementForm, final String message) {
		final ModelAndView res = new ModelAndView("advertisement/create");
		final PriceAdvertisement price = this.priceAdvertisementService.getPriceAdvertisement();
		res.addObject("advertisementForm", advertisementForm);
		res.addObject("formAction", "advertisement/subActor/create.do");
		res.addObject("price", price);
		res.addObject("error", message);
		return res;
	}
}
