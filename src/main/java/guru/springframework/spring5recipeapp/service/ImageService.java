package guru.springframework.spring5recipeapp.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Viktoriya on 11-Nov-20
 */
public interface ImageService {
	void saveImageFile(Long recipeId, MultipartFile imageFile);
}
