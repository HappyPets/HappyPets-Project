
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
import services.CategoryService;
import services.CommentService;
import controllers.AbstractController;
import domain.Category;
import domain.Comment;
import forms.CommentForm;

@Controller
@RequestMapping("/comment/subActor")
public class CommentSubActorController extends AbstractController {

	@Autowired
	private ActorService	actorService;

	@Autowired
	private CommentService	commentService;

	@Autowired
	private CategoryService	categoryService;


	// List -------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam final String category, @RequestParam(required = false) final String errorMessage) {
		ModelAndView res = new ModelAndView();
		final boolean admin = this.actorService.isAdmin();
		try {
			Assert.isTrue(this.actorService.isAuthenticated());
			res = new ModelAndView("comment/list");
			final int subActorLogueadoId = this.actorService.findByPrincipal().getId();
			final Collection<Comment> comments = this.commentService.getCommentsOfCategory(category);
			final Collection<Comment> commentsVet = this.commentService.getCommentsVet();
			res.addObject("comments", comments);
			res.addObject("category", category);
			res.addObject("commentsVet", commentsVet);
			res.addObject("subActorLogueadoId", subActorLogueadoId);
			res.addObject("error", errorMessage);
			res.addObject("admin", admin);

		} catch (final Throwable oops) {
			res.addObject("error", "error.list.comment");
		}
		return res;
	}

	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam final String category) {
		ModelAndView res = new ModelAndView();
		try {
			Assert.isTrue(!this.actorService.isAdmin());
			final CommentForm commentForm = new CommentForm();
			commentForm.setCategory(category);
			res = this.createEditModelAndView(commentForm);
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/welcome/index.do?errorMessage=" + "error.notAuthorize	");
		}
		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CommentForm commentForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(commentForm);
		else
			try {
				final Category category = this.categoryService.getCategoryByName(commentForm.getCategory());
				Comment comment = this.commentService.create();

				comment.setCategory(category);
				comment.setText(commentForm.getText());
				comment.setTitle(commentForm.getTitle());

				comment = this.commentService.save(comment);

				res = new ModelAndView("redirect:/comment/subActor/list.do?category=" + category.getName());
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(commentForm, "error.save.comment");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final CommentForm commentForm) {
		ModelAndView result;
		result = this.createEditModelAndView(commentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CommentForm commentForm, final String message) {
		final ModelAndView res = new ModelAndView("comment/create");
		res.addObject("commentForm", commentForm);
		res.addObject("formAction", "comment/subActor/create.do");
		res.addObject("error", message);
		return res;
	}
}
