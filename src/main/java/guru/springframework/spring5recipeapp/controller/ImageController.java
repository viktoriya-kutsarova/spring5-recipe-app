package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.service.ImageService;
import guru.springframework.spring5recipeapp.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Viktoriya on 11-Nov-20
 */
@Controller
public class ImageController {

	private final ImageService imageService;

	private final RecipeService recipeService;

	public ImageController(ImageService imageService, RecipeService recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable Long id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return "recipe/imageuploadform";
	}

	@PostMapping("recipe/{id}/image")
	public String uploadImage(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file) {

		imageService.saveImageFile(id, file);
		return "redirect:/recipe/" + id;
	}

}
