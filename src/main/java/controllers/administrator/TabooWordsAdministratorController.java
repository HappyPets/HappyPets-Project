
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.TabooWordsService;
import controllers.AbstractController;
import domain.TabooWords;
import forms.TabooWordsForm;

@Controller
@RequestMapping("/tabooWords/administrator")
public class TabooWordsAdministratorController extends AbstractController {

	@Autowired
	private TabooWordsService	tabooWordsService;

	@Autowired
	private ActorService		actorService;


	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView res;
		try {
			Assert.isTrue(this.actorService.isAdmin());
			final TabooWords tabooWords = this.tabooWordsService.getTabooWords();
			final TabooWordsForm tabooWordsForm = new TabooWordsForm(tabooWords);
			res = this.createEditModelAndView(tabooWordsForm);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.create");
		}
		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TabooWordsForm tabooWordsForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(tabooWordsForm);
		else
			try {
				final TabooWords tabooWords = this.tabooWordsService.getTabooWords();
				final String newWord = tabooWordsForm.getNewWord();
				final Collection<String> words = this.tabooWordsService.addWord(newWord);
				tabooWords.setWords(words);
				this.tabooWordsService.save(tabooWords);
				res = new ModelAndView("redirect:create.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(tabooWordsForm, "error.save");
			}
		return res;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(final String word) {
		ModelAndView res;
		try {
			Assert.isTrue(this.actorService.isAdmin());
			this.tabooWordsService.delete(word);
			res = new ModelAndView("redirect:create.do");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.delete");
		}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final TabooWordsForm tabooWordsForm) {
		ModelAndView result;
		result = this.createEditModelAndView(tabooWordsForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final TabooWordsForm tabooWordsForm, final String message) {
		final ModelAndView res = new ModelAndView("tabooWords/create");
		res.addObject("tabooWordsForm", tabooWordsForm);
		res.addObject("formAction", "tabooWords/administrator/create.do");
		res.addObject("message", message);
		return res;
	}
}
