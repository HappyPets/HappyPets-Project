
package controllers.user;

import java.util.ArrayList;
import java.util.Collections;

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
import services.MessageService;
import services.UserService;
import controllers.AbstractController;
import domain.Message;
import domain.User;
import forms.MessageForm;

@Controller
@RequestMapping("/message/user")
public class MessageUserController extends AbstractController {

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private UserService		userService;


	// Inbox List ---------------------------------------------------------
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView inbox(@RequestParam(required = false) final String delete) {
		final ModelAndView res = new ModelAndView("message/list");
		try {
			ArrayList<Message> receivedMessages = new ArrayList<Message>();
			receivedMessages = (ArrayList<Message>) this.messageService.getReceivedMessages();
			Collections.reverse(receivedMessages);
			res.addObject("messages", receivedMessages);
			res.addObject("inbox", true);
			res.addObject("error", "");
			res.addObject("delete", delete);
			res.addObject("requestURI", "message/user/inbox.do");
		} catch (final Throwable oops) {
			res.addObject("error", "message.error.inbox");
			res.addObject("inbox", true);
			res.addObject("requestURI", "message/user/inbox.do");
		}
		return res;
	}
	// Outbox List ---------------------------------------------------------
	@RequestMapping(value = "/outbox", method = RequestMethod.GET)
	public ModelAndView outbox(@RequestParam(required = false) final String delete, @RequestParam(required = false) final String create) {
		final ModelAndView res = new ModelAndView("message/list");
		try {
			ArrayList<Message> sendedMessages = new ArrayList<Message>();
			sendedMessages = (ArrayList<Message>) this.messageService.getSendedMessages();
			Collections.reverse(sendedMessages);
			res.addObject("messages", sendedMessages);
			res.addObject("outbox", true);
			res.addObject("error", "");
			res.addObject("delete", delete);
			res.addObject("create", create);
			res.addObject("requestURI", "message/user/outbox.do");
		} catch (final Throwable oops) {
			res.addObject("error", "message.error.outbox");
			res.addObject("outbox", true);
			res.addObject("requestURI", "message/user/outbox.do");
		}
		return res;
	}

	// Display ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId, @RequestParam(required = false) final String box) {
		final ModelAndView res = new ModelAndView("message/display");
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Message message = this.messageService.findOne(messageId);
			Assert.isTrue(message.getSender().equals(logued) || message.getReceiver().equals(logued));
			res.addObject("msg", message);
			res.addObject("box", box);
			res.addObject("error", "");
		} catch (final Throwable oops) {
			res.addObject("error", "message.error.display");
		}

		return res;
	}

	// Delete -------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId, @RequestParam(required = false) final String box) {
		ModelAndView res;
		try {
			final User logued = (User) this.actorService.findByPrincipal();
			final Message message = this.messageService.findOne(messageId);
			Assert.isTrue(message.getSender().equals(logued) || message.getReceiver().equals(logued));
			this.messageService.delete(messageId);
			res = new ModelAndView("redirect:/message/user/" + box + ".do?delete=true");
		} catch (final Throwable oops) {
			res = new ModelAndView("redirect:/message/user/" + box + ".do?delete=false");
		}

		return res;
	}

	// Create ----------------------------------------------------------
	@RequestMapping(value = "/create")
	public ModelAndView create(@RequestParam(required = false) final String create) {
		ModelAndView res = new ModelAndView();
		final MessageForm messageForm = new MessageForm();
		res = this.createEditModelAndView(messageForm);
		res.addObject("create", create);
		return res;
	}

	// Save ------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MessageForm messageForm, final BindingResult binding) {
		ModelAndView res = new ModelAndView();
		if (binding.hasErrors())
			res = this.createEditModelAndView(messageForm);
		else
			try {
				Message message = this.messageService.create();
				message.setReceiver(messageForm.getReceiver());
				message.setSubject(messageForm.getSubject());
				message.setText(messageForm.getText());
				message = this.messageService.save(message);
				message = this.messageService.sendMessage(message);

				res = new ModelAndView("redirect:/message/user/outbox.do?create=true");

			} catch (final Throwable oops) {
				res = new ModelAndView("redirect:/message/user/create.do?create=false");
			}
		return res;
	}

	// Ancillary methods -----------------------------------------------
	protected ModelAndView createEditModelAndView(final MessageForm messageForm) {
		ModelAndView result;
		result = this.createEditModelAndView(messageForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageForm messageForm, final String message) {
		final ModelAndView res = new ModelAndView("message/create");
		res.addObject("messageForm", messageForm);
		res.addObject("formAction", "message/user/create.do");
		res.addObject("users", this.userService.findAll());
		res.addObject("error", "");
		return res;
	}

}
