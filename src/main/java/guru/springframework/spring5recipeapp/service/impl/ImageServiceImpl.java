package guru.springframework.spring5recipeapp.service.impl;

import guru.springframework.spring5recipeapp.service.ImageService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Viktoriya on 11-Nov-20
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public void saveImageFile(Long recipeId, MultipartFile imageFile) {
		log.debug("Received a file");
	}
}
