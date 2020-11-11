package guru.springframework.spring5recipeapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.service.ImageService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;

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

	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
		RecipeCommand recipeCommand = recipeService.findCommandById(id);

		if (recipeCommand.getImage() != null) {
			Byte[] byteArray = recipeCommand.getImage();
			byte[] bytes = new byte[byteArray.length];
			int i = 0;
			for (Byte b : byteArray) {
				bytes[i++] = b;
			}
			response.setContentType("image/jpeg");
			InputStream is = new ByteArrayInputStream(bytes);
			IOUtils.copy(is, response.getOutputStream());
		}
	}

}
