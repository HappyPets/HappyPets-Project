
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	@Autowired
	private CommentService	commentService;


	// Ban -------------------------------
	@RequestMapping(value = "/ban")
	public ModelAndView ban(@RequestParam final int commentId) {
		ModelAndView res = new ModelAndView();
		final Comment comment = this.commentService.findOne(commentId);
		try {

			this.commentService.banComment(commentId);
			res = new ModelAndView("redirect:/comment/subActor/list.do?category=" + comment.getCategory().getName());
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/comment/subActorlist.do?category=" + comment.getCategory().getName() + "&errorMessage=" + "error.list.comment");
		}
		return res;
	}
}
