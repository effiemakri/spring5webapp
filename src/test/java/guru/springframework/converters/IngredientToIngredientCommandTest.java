package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "Cheeseburger";
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final Long UOM_ID = new Long(2L);

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty CategoryCommand, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }


    @Test
    public void convert() throws Exception {

        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        // when - create an IngredientCommand object by converting the Ingredient object
        IngredientCommand command = converter.convert(ingredient);

        // then
        assertNotNull(ingredient);
        assertNotNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
    }

    @Test
    public void convertWithNullUom() throws Exception {

        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        // when
        IngredientCommand command = converter.convert(ingredient);
        assertNotNull(ingredient);
        assertNull(command.getUom());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }

}