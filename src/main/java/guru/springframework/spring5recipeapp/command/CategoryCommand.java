package guru.springframework.spring5recipeapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Viktoriya on 23-Aug-20
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String description;
}
