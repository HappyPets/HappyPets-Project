/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import services.MessageService;
import domain.Advertisement;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}


	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private AdvertisementService	advertisementService;


	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false) final String errorMessage) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		String name = null;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		try {
			if (this.actorService.isAuthenticated())
				name = this.actorService.findByPrincipal().getUserAccount().getUsername();
		} catch (final Throwable oops) {

		}

		//Pasamos un anuncio publicado aleatorio
		final List<Advertisement> advertisements = (List<Advertisement>) this.advertisementService.getAdvertisementsNoBanned();
		Advertisement advertisement = new Advertisement();
		final int numero = (int) (Math.random() * advertisements.size());
		if (!advertisements.isEmpty()) {
			advertisement = advertisements.get(numero);
			Assert.notNull(advertisement);
		}

		//Notificar mensajes sin leer
		Integer unread = 0;
		try {
			if (this.actorService.isAuthenticated())
				if (this.actorService.isUser())
					unread = this.messageService.getUnreadMessages();
		} catch (final Throwable oops) {

		}

		result = new ModelAndView("welcome/index");
		result.addObject("unread", unread);
		result.addObject("name", name);
		result.addObject("moment", moment);
		result.addObject("advertisement", advertisement);
		result.addObject("errorMessage", errorMessage);

		return result;
	}
}
