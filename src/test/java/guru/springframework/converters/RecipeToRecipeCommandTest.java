package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "Recipe description";
    private static final Integer PREP_TIME = Integer.valueOf("10");
    private static final Integer COOK_TIME = Integer.valueOf("20");
    private static final Integer SERVINGS = Integer.valueOf("4");
    private static final String SOURCE = "Recipe source";
    private static final String URL = "Recipe url";
    private static final String DIRECTIONS = "Recipe directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long CATEGORY_ID1 = 1L;
    private static final Long CATEGORY_ID2 = 2L;
    private static final Long INGREDIENT_ID1 = 1L;
    private static final Long INGREDIENT_ID2 = 2L;
    private static final Long NOTES_ID = 1L;

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty Recipe, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    // Create a Recipe object, populate its fields, and check the value of the RecipeCommand fields after conversion
    @Test
    public void convert() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID1);
        ingredient2.setId(INGREDIENT_ID2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        // when
        RecipeCommand command = converter.convert(recipe);

        // then
        assertNotNull(recipe);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
        assertEquals(NOTES_ID, command.getNotes().getId());

    }

}