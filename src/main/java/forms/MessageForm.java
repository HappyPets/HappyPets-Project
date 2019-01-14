
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.User;

public class MessageForm {

	// Attributes -------------------------------------------------------------
	private int	id, version;
	private String	subject, text;
	private User	receiver;


	// Constructor ------------------------------------------------------------
	public MessageForm() {
		super();
	}

	// Getters and setters ----------------------------------------------------
	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@NotBlank
	@SafeHtml
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@SafeHtml
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Valid
	@NotNull
	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(final User receiver) {
		this.receiver = receiver;
	}

}
