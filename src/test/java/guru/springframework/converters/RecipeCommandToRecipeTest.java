package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.xmlunit.diff.Diff;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty RecipeCommand, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    // Create a RecipeCommand object, populate its fields, and check the value of the Recipe fields after conversion
    @Test
    public void convert() throws Exception {

        // given
        RecipeCommand command = new RecipeCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID2);
        command.getCategories().add(categoryCommand1);
        command.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID1);
        ingredientCommand2.setId(INGREDIENT_ID2);
        command.getIngredients().add(ingredientCommand1);
        command.getIngredients().add(ingredientCommand2);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        command.setNotes(notesCommand);

        // when
        Recipe recipe = converter.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }

}