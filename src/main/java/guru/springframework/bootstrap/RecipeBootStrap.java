package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Autowired
	public RecipeBootStrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		log.info("auto-wiring in the repositories");
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional			// make the spring framework create a transaction around the context
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading bootstrap data");
	}
	
	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		
		// Get UoMs
		Optional<UnitOfMeasure> noUomOptional = unitOfMeasureRepository.findByDescription("");
		if (!noUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		if (!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
		if (!pinchUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
		if (!ounceUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> poundUomOptional = unitOfMeasureRepository.findByDescription("Pound");
		if (!poundUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		UnitOfMeasure noUom = noUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pinchUom = pinchUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure ounceUom = ounceUomOptional.get();
		UnitOfMeasure poundUom = poundUomOptional.get();
		
		// get categories:
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if (!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
		if (!italianCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		Category italianCategory = italianCategoryOptional.get();
		
		// create the guacamole recipe
		log.info("Creating the Guacamole recipe");
		Recipe guacamoleRecipe = new Recipe();
		guacamoleRecipe.setDescription("The BEST guacamole!");
		guacamoleRecipe.setPrepTime(10);
		guacamoleRecipe.setCookTime(30);
		guacamoleRecipe.setServings(4);
		guacamoleRecipe.setSource("Simply Recipes");
		guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamoleRecipe.setDifficulty(Difficulty.EASY);
		
		// very redundant - could add helper method, and make this simpler
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(2), noUom, "avocados", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(0.5), teaSpoonUom, "Kosher salt", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(1), tableSpoonUom, "of fresh lime juice or lemon juice", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(2), tableSpoonUom, "of minced red onion or thinly sliced green onion", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(1), noUom, "serrano chiles, stems and seeds removed, minced", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(1), tableSpoonUom, "cilantro (leaves and tender stems), finely chopped", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(1), dashUom, "of freshly grated black pepper", guacamoleRecipe));
//		guacamoleRecipe.getIngredients().add(new Ingredient(new BigDecimal(1), noUom, "ripe tomato, seeds and pulp removed, chopped", guacamoleRecipe));
		
		// now with new convenience method:
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(2), noUom, "avocados"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(0.5), teaSpoonUom, "Kosher salt"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(1), tableSpoonUom, "of fresh lime juice or lemon juice"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(2), tableSpoonUom, "of minced red onion or thinly sliced green onion"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(1), noUom, "serrano chiles, stems and seeds removed, minced"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(1), tableSpoonUom, "cilantro (leaves and tender stems), finely chopped"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(1), dashUom, "of freshly grated black pepper"));
		guacamoleRecipe.addIngredient(new Ingredient(new BigDecimal(1), noUom, "ripe tomato, seeds and pulp removed, chopped"));
		
		StringBuilder guacamoleRecipeStringBuilder = new StringBuilder();
		guacamoleRecipeStringBuilder.append("\n - 1 Cut avocado, remove flesh")
				.append("\n - 2 Mash with a fork")
				.append("\n - 3 Add salt, lime juice, and the rest")
				.append("\n - 4 Cover with plastic and chill to store");
		guacamoleRecipe.setDirections(guacamoleRecipeStringBuilder.toString());
		
		Notes guacamoleRecipeNotes = new Notes();
		guacamoleRecipeNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
		// these are needed for bidirectional relationship - should be one method call (just in case one of them is omitted)
		// add some logic to the setter of the recipe object, setNotes method to take the setRecipe(this) on the Notes
		// guacamoleRecipeNotes.setRecipe(guacamoleRecipe);
		guacamoleRecipe.setNotes(guacamoleRecipeNotes);
		
		guacamoleRecipe.getCategories().add(americanCategory);
		guacamoleRecipe.getCategories().add(mexicanCategory);
		
		// create the spicy grilled chicken recipe
		log.info("Creating the spicy chicken recipe");
		Recipe spicyGrilledChickenRecipe = new Recipe();
		spicyGrilledChickenRecipe.setDescription("Spicy grilled chicken tacos!");
		spicyGrilledChickenRecipe.setPrepTime(20);
		spicyGrilledChickenRecipe.setCookTime(15);
		spicyGrilledChickenRecipe.setServings(4);
		spicyGrilledChickenRecipe.setSource("Simply Recipes");
		spicyGrilledChickenRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		spicyGrilledChickenRecipe.setDifficulty(Difficulty.MODERATE);
		
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(2), tableSpoonUom, "ancho chili poweder"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), teaSpoonUom, "dried oregano"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), teaSpoonUom, "dried cumin"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), teaSpoonUom, "sugar"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), teaSpoonUom, "salt"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), noUom, "clove of garlic, finely chopped"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), tableSpoonUom, "finely grated orange zest"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(3), tableSpoonUom, "fresh-squeezed orange juice"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(2), tableSpoonUom, "olive oil"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1.25), poundUom, "skinless, boneless chicken thighs"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(8), noUom, "small corn tortillas"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(3), cupUom, "packed baby arugula"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(2), noUom, "medium ripe avocados, sliced"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(4), noUom, "radishes, thinly sliced"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(40.5), pinchUom, "cherry tomatoes, halved"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(4), noUom, "roughly chopped cilantro"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(0.5), cupUom, "sour cream thinned with 1/4 cup milk"));
		spicyGrilledChickenRecipe.addIngredient(new Ingredient(new BigDecimal(1), noUom, "lime, cut into wedges"));

		StringBuilder spicyGrilledChickenRecipeStringBuilder = new StringBuilder();
		spicyGrilledChickenRecipeStringBuilder.append("\n - 1 Prepare a gas or charcoal grill for medium-high, direct heat.")
				.append("\n - 2 Make the marinade and coat the chicken")
				.append("\n - 3 Grill the chicken")
				.append("\n - 4 Warm the tortillas")
				.append("\n - 5 Assemble the tacos");
		spicyGrilledChickenRecipe.setDirections(spicyGrilledChickenRecipeStringBuilder.toString());
		
		Notes spicyGrilledChickenRecipeNotes = new Notes();
		spicyGrilledChickenRecipeNotes.setRecipe(spicyGrilledChickenRecipe);
		spicyGrilledChickenRecipeNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
		spicyGrilledChickenRecipe.setNotes(spicyGrilledChickenRecipeNotes);
		spicyGrilledChickenRecipe.getCategories().add(americanCategory);
		spicyGrilledChickenRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(guacamoleRecipe);
		recipes.add(spicyGrilledChickenRecipe);
		
		return recipes;
	}
	
}
