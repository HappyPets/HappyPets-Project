
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.SubActor;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private ActorService		actorService;


	// Constructor --------------------------------------------
	public CommentService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Comment create() {
		Assert.isTrue(!this.actorService.isAdmin());
		final Comment res = new Comment();
		res.setSubActor((SubActor) this.actorService.findByPrincipal());
		res.setIsBanned(false);
		return res;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(!this.actorService.isAdmin());
		comment.setPublicationMoment(new Date(Calendar.getInstance().getTimeInMillis() - 1000));
		final Comment saved = this.commentRepository.save(comment);
		return saved;
	}

	public Comment findOne(final int commentId) {
		final Comment res = this.commentRepository.findOne(commentId);
		return res;
	}

	public Collection<Comment> findAll() {
		final Collection<Comment> res = this.commentRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------
	//Devuelve los comentarios de una categoría dada
	public Collection<Comment> getCommentsOfCategory(final String category) {
		Collection<Comment> res = new ArrayList<Comment>();
		res = this.commentRepository.getCommentsByCategory(category);
		return res;
	}

	//Devuelve los comentarios que son de Veterinarios
	public Collection<Comment> getCommentsVet() {
		final Collection<Comment> res = new ArrayList<Comment>();
		for (final Comment c : this.findAll())
			if (this.actorService.checkActorRole("VET", c.getSubActor()))
				res.add(c);
		return res;
	}

	//Prohibe un comentario
	public void banComment(final int commentId) {
		Assert.isTrue(this.actorService.isAdmin());
		final Comment comment = this.findOne(commentId);
		Assert.notNull(comment);
		Assert.isTrue(!comment.getIsBanned());
		comment.setIsBanned(true);
	}

}
