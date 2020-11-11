package guru.springframework.spring5recipeapp.service.impl;

import java.io.IOException;
import java.util.stream.IntStream;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.service.ImageService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Viktoriya on 11-Nov-20
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile imageFile) {
		log.debug("Received a file");

		Recipe recipe = recipeRepository.findById(recipeId).get();

		try {
			byte[] bytes = imageFile.getBytes();
			Byte[] bytesObject = IntStream.range(0, bytes.length)
					.mapToObj(i -> bytes[i])
					.toArray(Byte[]::new);
			recipe.setImage(bytesObject);
			recipeRepository.save(recipe);
		}
		catch (IOException e) {
			//TODO handle the error
			log.error("Error occurred", e);
			e.printStackTrace();
		}

	}
}
