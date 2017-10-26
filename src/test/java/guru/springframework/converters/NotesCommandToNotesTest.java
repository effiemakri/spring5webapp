package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String RECIPE_NOTES = "Recipe Notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty CategoryCommand, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    // Create a NotesCommand object, populate its fields, and check the value of the fields after conversion to Notes
    @Test
    public void convert() throws Exception {

        // given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes notes = converter.convert(command);

        // then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}