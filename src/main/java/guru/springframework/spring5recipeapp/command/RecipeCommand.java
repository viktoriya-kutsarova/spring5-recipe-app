package guru.springframework.spring5recipeapp.command;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Viktoriya on 11-Jun-20
 */
@NoArgsConstructor
@Getter
@Setter
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private NotesCommand notes;
	private Set<CategoryCommand> categories = new HashSet<>();
	private Byte[] image;
}
