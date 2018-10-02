
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TabooWordsRepository;
import domain.Advertisement;
import domain.Comment;
import domain.JobOffer;
import domain.TabooWords;

@Service
@Transactional
public class TabooWordsService {

	@Autowired
	private TabooWordsRepository	tabooWordsRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private JobOfferService			jobOfferService;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private CommentService			commentService;


	// Constructor --------------------------------------------
	public TabooWordsService() {
		super();
	}

	// Crud methods -------------------------------------------

	public TabooWords findOne(final int tabooWordsId) {
		Assert.isTrue(this.actorService.isAdmin());
		final TabooWords res = this.tabooWordsRepository.findOne(tabooWordsId);
		return res;
	}

	public Collection<TabooWords> findAll() {
		final Collection<TabooWords> res = this.tabooWordsRepository.findAll();
		return res;
	}

	public TabooWords getTabooWords() {
		Assert.isTrue(this.actorService.isAdmin());
		final List<TabooWords> all = this.tabooWordsRepository.findAll();
		final TabooWords res = all.get(0);
		return res;
	}

	public TabooWords save(final TabooWords tabooWords) {
		Assert.notNull(tabooWords);
		Assert.isTrue(this.actorService.isAdmin());
		final TabooWords res = this.tabooWordsRepository.save(tabooWords);
		return res;
	}

	public void delete(final String word) {
		Assert.notNull(word);
		Assert.isTrue(this.actorService.isAdmin());
		final TabooWords tabooWords = this.getTabooWords();
		final Collection<String> words = tabooWords.getWords();
		words.remove(word);
		tabooWords.setWords(words);
		this.save(tabooWords);
	}

	// Other methods -------------------------------------
	public Collection<String> addWord(final String word) {
		Assert.notNull(word);
		Assert.isTrue(this.actorService.isAdmin());
		final Collection<String> words = this.getTabooWords().getWords();
		words.add(word);
		return words;
	}

	// Método que devuelve la lista de ofertas de trabajo con palabras tabú
	public Collection<JobOffer> getJobOfferWithTabooWords() {
		Assert.isTrue(this.actorService.isAdmin() || this.actorService.isUser());
		final Collection<JobOffer> allJobOffers = this.jobOfferService.findAll();
		final Collection<TabooWords> tabooWords = this.findAll();
		final Collection<JobOffer> res = new ArrayList<JobOffer>();
		for (final JobOffer jobOffer : allJobOffers)
			for (final TabooWords tabooWord : tabooWords)
				for (final String word : tabooWord.getWords())
					if (jobOffer.getCity().toLowerCase().contains(word.toLowerCase()) || jobOffer.getDescription().toLowerCase().contains(word.toLowerCase()) || jobOffer.getTitle().toLowerCase().contains(word.toLowerCase())) {
						res.add(jobOffer);
						break;
					}
		return res;
	}

	// Método que devuelve la lista de anuncios con palabras tabú
	public Collection<Advertisement> getAdvertisementsWithTabooWords() {
		Assert.isTrue(this.actorService.isAdmin() || this.actorService.isUser());
		final Collection<Advertisement> allAdvertisements = this.advertisementService.findAll();
		final Collection<TabooWords> tabooWords = this.findAll();
		final Collection<Advertisement> res = new ArrayList<Advertisement>();
		for (final Advertisement advertisement : allAdvertisements)
			for (final TabooWords tabooWord : tabooWords)
				for (final String word : tabooWord.getWords())
					if (advertisement.getBanner().toLowerCase().contains(word.toLowerCase()) || advertisement.getTargetPage().toLowerCase().contains(word.toLowerCase())) {
						res.add(advertisement);
						break;
					}
		return res;
	}

	// Método que devuelve la lista de comentarios con palabras tabú
	public Collection<Comment> getCommentsWithTabooWords() {
		Assert.isTrue(this.actorService.isAdmin() || this.actorService.isUser());
		final Collection<Comment> allComments = this.commentService.findAll();
		final Collection<TabooWords> tabooWords = this.findAll();
		final Collection<Comment> res = new ArrayList<Comment>();
		for (final Comment comment : allComments)
			for (final TabooWords tabooWord : tabooWords)
				for (final String word : tabooWord.getWords())
					if (comment.getText().toLowerCase().contains(word.toLowerCase()) || comment.getTitle().toLowerCase().contains(word.toLowerCase())) {
						res.add(comment);
						break;
					}
		return res;
	}
}
