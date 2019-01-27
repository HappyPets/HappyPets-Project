
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;
import domain.User;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	private ActorService		actorService;


	// Constructor --------------------------------------------
	public MessageService() {
		super();
	}

	// Crud methods -------------------------------------------

	public Message create() {
		Assert.isTrue(this.actorService.isUser());
		final Message message = new Message();
		final User logued = (User) this.actorService.findByPrincipal();
		message.setSendMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		message.setSender(logued);
		message.setIsCopy(false);
		message.setIsRead(false);
		return message;
	}

	public Message save(Message message) {
		Assert.notNull(message);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(message.getSender()));
		message = this.messageRepository.save(message);
		return message;
	}
	public Message saveRead(Message message) {
		Assert.notNull(message);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(message.getReceiver()) || logued.equals(message.getSender()));
		message = this.messageRepository.save(message);
		return message;
	}

	public void delete(final int messageId) {
		Assert.isTrue(this.actorService.isUser());
		Assert.isTrue(messageId != 0);
		final User logued = (User) this.actorService.findByPrincipal();
		final Message message = this.findOne(messageId);
		Assert.isTrue(message.getSender().equals(logued) || message.getReceiver().equals(logued));
		this.messageRepository.delete(messageId);
	}

	public Message findOne(final int messageId) {
		final Message res = this.messageRepository.findOne(messageId);
		return res;
	}

	public Collection<Message> findAll() {
		final Collection<Message> res = this.messageRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método que obtiene los mensajes enviados por el usuario logueado
	public Collection<Message> getSendedMessages() {
		Assert.isTrue(this.actorService.isUser());
		final Collection<Message> res = new ArrayList<Message>();
		final User logued = (User) this.actorService.findByPrincipal();
		final Collection<Message> sendedMessages = logued.getSendedMessages();
		for (final Message message : sendedMessages)
			if (message.getIsCopy())
				res.add(message);
		return res;
	}

	// Método que obtiene los mensajes recibidos por el usuario logueado
	public Collection<Message> getReceivedMessages() {
		Assert.isTrue(this.actorService.isUser());
		final Collection<Message> res = new ArrayList<Message>();
		final User logued = (User) this.actorService.findByPrincipal();
		final Collection<Message> receivedMessages = logued.getReceivedMessages();
		for (final Message message : receivedMessages)
			if (!message.getIsCopy())
				res.add(message);
		return res;
	}

	// Método para enviar un mensaje. Devuelve la copia de inbox del mensaje
	public Message sendMessage(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		Assert.isTrue(logued.equals(message.getSender()));

		Message message_ = this.create();
		message_.setIsCopy(true);
		message_.setIsRead(true);
		message_.setSubject(message.getSubject());
		message_.setText(message.getText());
		message_.setReceiver(message.getReceiver());
		message_ = this.save(message_);

		return message;
	}

	// Método para econtar numero de mensajes no leídos
	public Integer getUnreadMessages() {
		Integer res = 0;
		Assert.isTrue(this.actorService.isUser());
		final User logued = (User) this.actorService.findByPrincipal();
		final Collection<Message> messages = logued.getReceivedMessages();
		final Collection<Message> unreadMessages = new ArrayList<Message>();
		for (final Message m : messages)
			if (m.getIsRead() == false)
				unreadMessages.add(m);

		if (unreadMessages.size() != 0)
			res = unreadMessages.size();

		return res;
	}
}
