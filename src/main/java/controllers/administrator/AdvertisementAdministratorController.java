
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import controllers.AbstractController;
import domain.Advertisement;

@Controller
@RequestMapping("/advertisement/administrator")
public class AdvertisementAdministratorController extends AbstractController {

	@Autowired
	private AdvertisementService	advertisementService;


	// Ban -------------------------------
	@RequestMapping(value = "/ban")
	public ModelAndView ban(@RequestParam final int advertisementId) {
		ModelAndView res = new ModelAndView();
		try {
			this.advertisementService.banAdvertisement(advertisementId);
			res = new ModelAndView("redirect:/advertisement/administrator/list.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/advertisement/administrator/list.do?errorMessage=" + "error.list.advertisement");
		}
		return res;
	}
	// List -------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required = false) final String errorMessage) {
		ModelAndView res = new ModelAndView();
		try {
			res = new ModelAndView("advertisement/listOwn");
			final Collection<Advertisement> advertisements = this.advertisementService.findAll();
			res.addObject("advertisements", advertisements);
			res.addObject("admin", true);
			res.addObject("error", errorMessage);
		} catch (final Throwable oops) {
			res.addObject("error", "error.list.advertisement");
		}
		return res;
	}
}
