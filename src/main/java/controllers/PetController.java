/*
 * ProfileController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.PetService;
import domain.Category;
import domain.Pet;

@Controller
@RequestMapping("/pet")
public class PetController extends AbstractController {

	@Autowired
	private PetService		petService;

	@Autowired
	private CategoryService	categoryService;


	// List ---------------------------------------------------------------		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String category) {
		final ModelAndView res = new ModelAndView("pet/list");
		try {
			Assert.notNull(category);
			Assert.isTrue(!category.equals(""));
			final Category c = this.categoryService.getCategoryByName(category);
			final Collection<Pet> allPetsInAdoption = this.petService.getAllPetsInAdoption();
			final Collection<Pet> petsInAdoptionByCategory = this.petService.getPetsByCategory(c, allPetsInAdoption);
			res.addObject("pets", petsInAdoptionByCategory);
			res.addObject("category", "pet." + category);
			res.addObject("error", "");
		} catch (final Throwable oops) {
			res.addObject("error", "error.list");
		}
		return res;
	}

	// Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int petId) {
		final ModelAndView res = new ModelAndView("pet/display");
		try {
			final Pet pet = this.petService.findOne(petId);
			res.addObject("pet", pet);
			res.addObject("error", "");
		} catch (final Throwable oops) {
			res.addObject("error", "error.display");
		}

		return res;
	}

}
