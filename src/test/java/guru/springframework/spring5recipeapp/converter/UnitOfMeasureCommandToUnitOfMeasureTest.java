package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "descr";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	@BeforeEach
	void setUp() {
		unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	void testNullParameter() {
		assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
	}

	@Test
	void testEmptyObject() {
		assertNotNull(unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
	}

	@Test
	void convert() throws Exception {
		//given
		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setDescription(DESCRIPTION);
		unitOfMeasureCommand.setId(LONG_VALUE);

		//when
		UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);

		//then
		assertNotNull(unitOfMeasure);
		assertEquals(LONG_VALUE, unitOfMeasure.getId());
		assertEquals(DESCRIPTION, unitOfMeasure.getUom());

	}
}