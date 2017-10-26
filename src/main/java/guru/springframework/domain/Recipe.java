package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // special type to relational DBs that will support the
														// automatic generation of a sequence
														// for MySQL: AUTO
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	@Lob
	private String directions;
	
	@Enumerated(value=EnumType.STRING)
	private Difficulty difficulty;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="recipe")	// recipe will own this relationship (cascade)
									// MappedBy: property on the child class is recipe - defines relationship from Recipe class
									// i.e. this recipe will be stored in a property on the child class, OR
									// the set of ingredients on each object of type Ingredient is going to be in a property called "recipe"
									// So. mappedBy="recipe" - this is the target property in the Ingredient class
	private Set<Ingredient> ingredients = new HashSet<>();					// Set: to get a unique set of ingredients
	

	@ManyToMany						// We need to configure this, as Hibernate will give us two join tables - which we do not want
	@JoinTable(name="recipe_category", 
		joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@Lob													// JPA will store this in the DB as a BLOB field
	private Byte[] image;
	
	@OneToOne(cascade = CascadeType.ALL)					// Recipe is the owner of this. (if we delete Recipe in the DB, Notes will also be deleted)
	private Notes notes;

	public void setNotes(Notes notes) {
		if (notes != null) {
			this.notes = notes;
			notes.setRecipe(this);
		}
	}
	
	// new helper method: convenience method that encapsulates the logic in one spot.
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Recipe: ")
			.append("\n").append("Description: ").append(description)
			.append("\n").append("Prep Time: ").append(prepTime)
			.append("\n").append("Cook Time: ").append(cookTime)
			.append("\n").append("Servings: ").append(servings)
			.append("\n").append("Source: ").append(source)
			.append("\n").append("URL: ").append(url)
			.append("\n").append("Difficulty: ").append(difficulty)
			.append("\n").append("Ingredients: ").append(ingredients)
			.append("\n").append("Directions:").append(directions)
			.append("\n").append("Categories: ").append(categories)
			.append("\n").append("Notes: ").append(notes);

		return sb.toString();
	}
}
