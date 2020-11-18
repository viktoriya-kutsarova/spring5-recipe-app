package guru.springframework.spring5recipeapp.command;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/**
 * Created by Viktoriya on 11-Jun-20
 */
@NoArgsConstructor
@Getter
@Setter
public class RecipeCommand {
	private Long id;
	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	@Min(1)
	@Max(999)
	private Integer prepTime;
	@Min(1)
	@Max(999)
	private Integer cookTime;
	@Min(1)
	@Max(100)
	private Integer servings;
	private String source;
	@URL
	private String url;
	@NotBlank
	private String directions;
	private Difficulty difficulty;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private NotesCommand notes;
	private Set<CategoryCommand> categories = new HashSet<>();
	private Byte[] image;
}
