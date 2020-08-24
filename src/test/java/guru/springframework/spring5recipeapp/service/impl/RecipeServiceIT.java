package guru.springframework.spring5recipeapp.service.impl;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@DataJpaTest - this will load very lightweight context,
// we actually need the SpringBootTest to load the whole Spring Context
class RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "New descr";

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@BeforeEach
	void setUp() {
	}

	@Test
	@Transactional
	void save() {
		//given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		//when
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.save(testRecipeCommand);

		//then
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
//		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
//		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}
}