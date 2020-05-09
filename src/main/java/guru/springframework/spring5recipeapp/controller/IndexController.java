package guru.springframework.spring5recipeapp.controller;

import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Viktoriya on 01-Mar-20
 */
@Slf4j
@Controller
public class IndexController {

	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "/", "", "/index" })
	public String getIndexPage(Model model) {
		log.debug("Entering index controller");

		Set<Recipe> recipes = recipeService.getRecipes();
		System.out.println(recipes);
		model.addAttribute("recipes", recipes);
		return "recipes/index";
	}
}
