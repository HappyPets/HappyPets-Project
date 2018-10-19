
package forms;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.TabooWords;

public class TabooWordsForm {

	// Attributes --------------------------------------------------------------
	private int					id, version;
	private Collection<String>	words;
	private String				newWord;


	// Constructor -------------------------------------------------------------
	public TabooWordsForm() {
		super();
	}

	public TabooWordsForm(final TabooWords tabooWords) {
		this.id = tabooWords.getId();
		this.version = tabooWords.getVersion();
		this.words = tabooWords.getWords();
	}

	// Getters and setters -----------------------------------------------------
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

	@NotNull
	@ElementCollection
	public Collection<String> getWords() {
		return this.words;
	}

	public void setWords(final Collection<String> words) {
		this.words = words;
	}

	@SafeHtml
	@NotBlank
	public String getNewWord() {
		return this.newWord;
	}

	public void setNewWord(final String newWord) {
		this.newWord = newWord;
	}

}
