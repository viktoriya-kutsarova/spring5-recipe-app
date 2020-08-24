package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecipeCommandToRecipeTest {

	private final String DESCRIPTION = "descr";

	private final Long ID = 1l;

	RecipeCommandToRecipe recipeCommandToRecipe;

	IngredientsCommandToIngredients ingredientsCommandToIngredients;

	NotesCommandToNotes notesCommandToNotes;

	CategoryCommandToCategory categoryCommandToCategory;

	@BeforeEach
	void setUp() {
		recipeCommandToRecipe = new RecipeCommandToRecipe(ingredientsCommandToIngredients, categoryCommandToCategory, notesCommandToNotes);
	}

	@Test
	void testNullConvertion() {
		assertNull(recipeCommandToRecipe.convert(null));
	}

	@Test
	void testEmpty() {
		assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
	}

	@Test
	void testConvertingObject() {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(ID);
		recipeCommand.setDescription(DESCRIPTION);

		//when
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

		//then
		assertNotNull(recipe);
		assertEquals(ID, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());

	}
}