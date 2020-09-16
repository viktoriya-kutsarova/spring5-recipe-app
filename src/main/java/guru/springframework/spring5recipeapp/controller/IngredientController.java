package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Viktoriya on 13-Sep-20
 */
@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/ingredients")
	public String getIngredients(@PathVariable String id, Model model) {
		log.debug("Getting recipe id for ingredient controller");

		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

		return "recipe/ingredient/list";
	}
}
