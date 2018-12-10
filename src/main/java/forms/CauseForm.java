
package forms;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import domain.Cause;
import domain.Priority;

public class CauseForm {

	// Attributes -------------------------------------------------------------

	private int			id, version;
	private String		picture, title, description;
	private Priority	priority;


	// Constructor ------------------------------------------------------------

	public CauseForm() {
		super();
	}

	public CauseForm(final Cause cause) {
		this.id = cause.getId();
		this.version = cause.getVersion();
		this.picture = cause.getPicture();
		this.title = cause.getTitle();
		this.description = cause.getDescription();
		this.priority = cause.getPriority();
	}

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
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(final Priority priority) {
		this.priority = priority;
	}
}
