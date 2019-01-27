
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	subject, text;
	private Date	sendMoment;
	private boolean	isCopy;
	private boolean	isRead;


	// Getters and setters ----------------------------------------------------
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

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	public Date getSendMoment() {
		return this.sendMoment;
	}

	public void setSendMoment(final Date sendMoment) {
		this.sendMoment = sendMoment;
	}

	public boolean getIsCopy() {
		return this.isCopy;
	}

	public void setIsCopy(final boolean isCopy) {
		this.isCopy = isCopy;
	}

	public boolean getIsRead() {
		return this.isRead;
	}

	public void setIsRead(final boolean isRead) {
		this.isRead = isRead;
	}


	// Relationships ----------------------------------------------------------
	private User	sender;
	private User	receiver;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getSender() {
		return this.sender;
	}

	public void setSender(final User sender) {
		this.sender = sender;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(final User receiver) {
		this.receiver = receiver;
	}

}
