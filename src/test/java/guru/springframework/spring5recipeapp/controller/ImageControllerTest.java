package guru.springframework.spring5recipeapp.controller;

import java.util.stream.IntStream;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.service.ImageService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Viktoriya on 11-Nov-20
 */
public class ImageControllerTest {

	@Mock
	ImageService imageService;

	@Mock
	RecipeService recipeService;

	ImageController imageController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		imageController = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController)
				.setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}

	@Test
	public void getImageForm() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		mockMvc.perform(get("/recipe/1/image"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void handlePostImage() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
				"text/plain", "SPRING".getBytes());

		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/recipe/1"));

		verify(imageService, times(1)).saveImageFile(anyLong(), any());
	}

	@Test
	public void renderImageFromDB() throws Exception {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		String s = "Fake image test";
		byte[] bytes = s.getBytes();
		Byte[] bytesObject = IntStream.range(0, bytes.length)
				.mapToObj(i -> bytes[i])
				.toArray(Byte[]::new);

		recipeCommand.setImage(bytesObject);

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		//when
		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		byte[] responseBytes = response.getContentAsByteArray();
		assertEquals(s.getBytes().length, responseBytes.length);

	}

	@Test
	public void testGetImageNumberFormatException() throws Exception {
		mockMvc.perform(get("/recipe/anystring/image"))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("400error"));
	}
}
