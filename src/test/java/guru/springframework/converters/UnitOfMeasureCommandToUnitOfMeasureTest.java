package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty CategoryCommand, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    // Create a UnitOfMeasureCommand object, populate its fields,
    // and check the value of the fields UnitOfMeasure object after conversion
    @Test
    public void convert() throws Exception {

        // given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure uom = converter.convert(command);

        // then
        assertNotNull(uom);
        assertEquals(ID_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }

}