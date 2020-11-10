package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.service.IngredientService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import guru.springframework.spring5recipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Viktoriya on 13-Sep-20
 */
@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;

	private final IngredientService ingredientService;

	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping("/recipe/{id}/ingredients")
	public String getIngredients(@PathVariable String id, Model model) {
		log.debug("Getting recipe id for ingredient controller");

		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

		return "recipe/ingredient/list";
	}

	@GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		log.debug("Getting recipe id for ingredient controller");

		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

		return "recipe/ingredient/show";
	}

	@GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

		return "recipe/ingredient/ingredientform";
	}

	@PostMapping("/recipe/{recipeId}/ingredient")
	public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

		log.debug("saved receipe id:" + savedIngredient.getRecipeId());
		log.debug("saved ingredient id:" + savedIngredient.getId());

		return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId();
	}

	@GetMapping("/recipe/{recipeId}/ingredients/new")
	public String newIngredient(@PathVariable Long recipeId, Model model) {
		RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
		//TODO raise exception if not present
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(recipeId);
		ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
		model.addAttribute("ingredient", ingredientCommand);
		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

		return "recipe/ingredient/ingredientform";
	}

	@GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/delete")
	public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
		ingredientService.deleteById(recipeId, ingredientId);

		return "redirect:/recipe/" + recipeId + "/ingredients";
	}

}
