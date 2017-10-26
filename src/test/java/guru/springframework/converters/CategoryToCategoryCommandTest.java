package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    // Test that the convert method works correctly (i.e. returns a null), when the source is null:
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    // Pass in an empty CategoryCommand, and make sure it is not null when converted.
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    // Create a Category object, populate its fields, and check the value of the fields after conversion
    @Test
    public void convert() throws Exception {
        // given
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        // when
        CategoryCommand categoryCommand = converter.convert(category);

        // then
        assertNotNull(categoryCommand);
        assertEquals(LONG_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }

}