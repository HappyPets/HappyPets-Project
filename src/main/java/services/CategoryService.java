
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;

	@Autowired
	private ActorService		actorService;


	// Constructor --------------------------------------------
	public CategoryService() {
		super();
	}

	// Crud methods -------------------------------------------
	public Category findOne(final int categoryId) {
		final Category res = this.categoryRepository.findOne(categoryId);
		return res;
	}

	public Collection<Category> findAll() {
		final Collection<Category> res = this.categoryRepository.findAll();
		return res;
	}

	// Other methods -------------------------------------

	// Método para encontrar una categoría por su nombre
	public Category getCategoryByName(final String name) {
		Category res = null;
		for (final Category category : this.findAll())
			if (category.getName().equals(name)) {
				res = category;
				break;
			}
		return res;
	}

	//Lista de categorías ordenadas según el no de adopciones aceptadas.
	public Collection<Category> categoriesOrderByNumAdoptionRequestAccepted() {
		Assert.isTrue(this.actorService.isAdmin());
		return this.categoryRepository.categoriesOrderByNumAdoptionRequestAccepted();
	}
}
