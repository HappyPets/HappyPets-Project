
package controllers.subactor;

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
import services.CauseService;
import services.CreditCardService;
import services.DonationService;
import controllers.AbstractController;
import domain.Cause;
import domain.CreditCard;
import domain.Donation;
import forms.DonationForm;

@Controller
@RequestMapping("/donation/subActor")
public class DonationSubActorController extends AbstractController {

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CauseService		causeService;

	@Autowired
	private DonationService		donationService;

	@Autowired
	private CreditCardService	creditCardService;


	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final int causeId) {
		ModelAndView res = new ModelAndView();
		DonationForm donationForm = new DonationForm();
		try {
			donationForm = new DonationForm();
			donationForm.setCauseId(causeId);
			res = this.createEditModelAndView(donationForm);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
		}
		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final DonationForm donationForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(donationForm);
		else
			try {
				final Cause cause = this.causeService.findOne(donationForm.getCauseId());
				Donation donation = this.donationService.create(cause);

				//Comprobamos que la creditcard introducida es válida
				final CreditCard creditCard = new CreditCard();
				creditCard.setHolderName(donationForm.getHolderName());
				creditCard.setBrandName(donationForm.getBrandName());
				creditCard.setNumber(donationForm.getNumber());
				creditCard.setExpirationMonth(donationForm.getExpirationMonth());
				creditCard.setExpirationYear(donationForm.getExpirationYear());
				creditCard.setCvv(donationForm.getCvv());

				Assert.isTrue(this.creditCardService.isValidCreditCard2(creditCard));

				donation.setCreditCard(creditCard);
				donation.setQuantity(donationForm.getQuantity());

				donation = this.donationService.save(donation);

				res = new ModelAndView("redirect:/cause/subActor/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(donationForm, "error.save.donation");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final DonationForm donationForm) {
		ModelAndView result;
		result = this.createEditModelAndView(donationForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final DonationForm donationForm, final String message) {
		final ModelAndView res = new ModelAndView("donation/create");
		res.addObject("donationForm", donationForm);
		res.addObject("formAction", "donation/subActor/create.do");
		res.addObject("error", message);
		return res;
	}
}
