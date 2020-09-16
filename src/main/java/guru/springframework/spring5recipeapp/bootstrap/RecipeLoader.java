package guru.springframework.spring5recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.respository.CategoryRepository;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.respository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 24-Apr-20
 */
@Slf4j
@Component
public class RecipeLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final RecipeRepository recipeRepository;

	private final CategoryRepository categoryRepository;

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		log.debug("Loading bootstrap data");
		recipeRepository.saveAll(getRecipes());
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>(2);

		Optional<UnitOfMeasure> optionalTeaspoon = unitOfMeasureRepository.findByUom("Teaspoon");
		if (optionalTeaspoon.isEmpty()) {
			throw new RuntimeException("Expected Teaspoon UOM not found");
		}
		Optional<UnitOfMeasure> optionalTablespoon = unitOfMeasureRepository.findByUom("Tablespoon");
		if (optionalTablespoon.isEmpty()) {
			throw new RuntimeException("Expected Tablespoon UOM not found");
		}
		Optional<UnitOfMeasure> optionalDash = unitOfMeasureRepository.findByUom("Dash");
		if (optionalDash.isEmpty()) {
			throw new RuntimeException("Expected Dash UOM not found");
		}

		Optional<UnitOfMeasure> optionalCup = unitOfMeasureRepository.findByUom("Cup");
		if (optionalCup.isEmpty()) {
			throw new RuntimeException("Expected Cup UOM not found");
		}

		Optional<UnitOfMeasure> optionalClove = unitOfMeasureRepository.findByUom("Clove");
		if (optionalClove.isEmpty()) {
			throw new RuntimeException("Expected Clove UOM not found");
		}

		Optional<UnitOfMeasure> optionalPound = unitOfMeasureRepository.findByUom("Pound");
		if (optionalPound.isEmpty()) {
			throw new RuntimeException("Expected Pound UOM not found");
		}

		Optional<UnitOfMeasure> optionalPint = unitOfMeasureRepository.findByUom("Pint");
		if (optionalPint.isEmpty()) {
			throw new RuntimeException("Expected Pint UOM not found");
		}

		Optional<Category> optionalMexican = categoryRepository.findByDescription("Mexican");
		if (optionalMexican.isEmpty()) {
			throw new RuntimeException("Expected Mexican Category not found");
		}

		Optional<Category> optionalGrill = categoryRepository.findByDescription("Grill");
		if (optionalGrill.isEmpty()) {
			throw new RuntimeException("Expected Grill Category not found");
		}

		UnitOfMeasure teaspoon = optionalTeaspoon.get();
		UnitOfMeasure tablespoon = optionalTablespoon.get();
		UnitOfMeasure dash = optionalDash.get();
		UnitOfMeasure cup = optionalCup.get();
		UnitOfMeasure clove = optionalClove.get();
		UnitOfMeasure pound = optionalPound.get();
		UnitOfMeasure pint = optionalPint.get();

		Category mexican = optionalMexican.get();
		Category grill = optionalGrill.get();

		Recipe guacamole = new Recipe();
		guacamole.setDescription("Perfect Guacamole");
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamole.setPrepTime(10);
		guacamole.setServings(4);
		guacamole.setDifficulty(Difficulty.EASY);
		guacamole.setDirections("""
				1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. 
				Score the inside of the avocado with a blunt knife and scoop out the flesh 
				with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.
				 
				2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! 
				The guacamole should be a little chunky.)
								
				3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) 
				juice. The acid in the lime juice will provide some balance to the richness of 
				the avocado and will help delay the avocados from turning brown.
				    
				Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers 
				vary individually in their hotness. So, start with a half of one chili pepper 
				and add to the guacamole to your desired degree of hotness.
				    
				Remember that much of this is done to taste because of the variability in 
				the fresh ingredients. Start with this recipe and adjust to your taste.
				    
				Chilling tomatoes hurts their flavor, so if you want to add chopped tomato 
				to your guacamole, add it just before serving.
								
				4 Serve: Serve immediately, or if making a few hours ahead, place plastic 
				wrap on the surface of the guacamole and press down to cover it and to 
				prevent air reaching it. (The oxygen in the air causes oxidation which will 
				turn the guacamole brown.) Refrigerate until ready to serve.""");

		Notes guacNote = new Notes();
		guacNote.setRecipeNotes("""
				THE BEST WAY TO CUT AN AVOCADO
				To slice open an avocado, cut it in half lengthwise with a sharp chef’s knife 
				and twist apart the sides. One side will have the pit. To remove it, you can 
				do one of two things:
				    
				Method #1: Gently tap the pit with your chef’s knife so the knife gets 
				wedged into the pit. Twist your knife slightly to dislodge the pit and lift 
				to remove. If you use this method, first protect your hand with a thick kitchen 
				towel before proceeding.
				Method #2: Cut the side with the pit in half again, exposing more of the pit. 
				Use your fingers or a spoon to remove the pit
				Once the pit is removed, just cut the avocado into chunks right inside the peel 
				and use a spoon to scoop them out.
				    
				Still curious? Read more: https://www.simplyrecipes.com/recipes/how_to_cut_and_peel_an_avocado/""");
		guacamole.setNotes(guacNote);

		guacamole.getCategories().add(mexican);

		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(2), "ripe tomatoes"));
		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(0.25), teaspoon, "salt"));
		guacamole.addIngredient(new Ingredient(BigDecimal.ONE, tablespoon, "fresh lime juice or lemon juice"));
		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(2), tablespoon, "minced red onion or thinly sliced green onion"));
		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(2), "serrano chiles, stems and seeds removed, minced"));
		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(2), tablespoon, "cilantro (leaves and tender stems), finely chopped"));
		guacamole.addIngredient(new Ingredient(BigDecimal.ONE, dash, "freshly grated black pepper"));
		guacamole.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), "ripe tomato, seeds and pulp removed, chopped"));
		guacamole.addIngredient(new Ingredient("Red radishes or jicama, to garnish"));
		guacamole.addIngredient(new Ingredient("Tortilla chips, to serve"));

		recipes.add(guacamole);

		log.debug("Created guacamole recipe");

		Recipe spicyGrilledChicken = new Recipe();
		spicyGrilledChicken.setDescription("Spicy Grilled Chicken Tacos");
		spicyGrilledChicken.setPrepTime(20);
		spicyGrilledChicken.setCookTime(15);
		spicyGrilledChicken.setServings(4);
		spicyGrilledChicken.setDifficulty(Difficulty.MODERATE);
		spicyGrilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		spicyGrilledChicken.setDirections("""
				1 Prepare a gas or charcoal grill for medium-high, direct heat.
				    
				2 Make the marinade and coat the chicken: In a large bowl, stir together 
				the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. 
				Stir in the orange juice and olive oil to make a loose paste. Add the chicken 
				to the bowl and toss to coat all over.
				    
				Set aside to marinate while the grill heats and you prepare the rest of the 
				toppings.
								
				3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a 
				thermometer inserted into the thickest part of the meat registers 165F. 
				Transfer to a plate and rest for 5 minutes.
				    
				4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet 
				over medium-high heat. As soon as you see pockets of the air start to puff up 
				in the tortilla, turn it with tongs and heat for a few seconds on the other side.
				    
				Wrap warmed tortillas in a tea towel to keep them warm until serving.
				    
				5 Assemble the tacos: Slice the chicken into strips. On each tortilla, 
				place a small handful of arugula. Top with chicken slices, sliced avocado, 
				radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. 
				Serve with lime wedges.""");

		Notes grilledChickenNotes = new Notes();
		grilledChickenNotes.setRecipeNotes("""
				We have a family motto and it is this: Everything goes better in a tortilla.
				                                                                                               
				Any and every kind of leftover can go inside a warm tortilla, usually with 
				a healthy dose of pickled jalapenos. I can always sniff out a late-night 
				snacker when the aroma of tortillas heating in a hot pan on the stove 
				comes wafting through the house.""");
		spicyGrilledChicken.setNotes(grilledChickenNotes);

		spicyGrilledChicken.getCategories().add(grill);

		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(2), tablespoon, "ancho chili powder"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, teaspoon, "dried oregano"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, teaspoon, "dried cumin"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, teaspoon, "sugar"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), teaspoon, "salt"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, clove, "garlic, finely chopped"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, tablespoon, "finely grated orange zest"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(3), tablespoon, "fresh-squeezed orange juice"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(2), tablespoon, "olive oil"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(1.25), pound, "skinless, boneless chicken thighs"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(8), "small corn tortillas"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(3), cup, "packed baby arugula"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(2), "medium ripe avocados, sliced"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(4), "radishes, thinly sliced"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), pint, "cherry tomatoes, halved"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(0.25), "red onion, thinly sliced"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, "Roughly chopped cilantro"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.valueOf(0.5), cup, "sour cream thinned with 1/4 cup milk"));
		spicyGrilledChicken.addIngredient(new Ingredient(BigDecimal.ONE, "lime, cut into wedges"));

		recipes.add(spicyGrilledChicken);

		log.debug("Created spicy grilled chicken recipe");


		return recipes;
	}

}
