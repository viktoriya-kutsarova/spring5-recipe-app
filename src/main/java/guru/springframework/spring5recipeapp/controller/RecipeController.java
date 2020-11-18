package guru.springframework.spring5recipeapp.controller;

import javax.validation.Valid;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Viktoriya on 06-Jun-20
 */
@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private static final String VIEW_RECIPE_FORM = "recipe/recipeform";
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("{id}")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping("new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());

		return VIEW_RECIPE_FORM;
	}

	@PostMapping
	public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> log.debug(error.toString()));
			return VIEW_RECIPE_FORM;
		}
		RecipeCommand savedCommand = recipeService.save(command);

		return "redirect:/recipe/" + savedCommand.getId();
	}

	@GetMapping("{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	@DeleteMapping("{id}/delete")
	public String deleteRecipe(@PathVariable String id, Model model) {

		log.debug("Deleting id: " + id);

		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception");
		log.error(exception.getMessage());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
