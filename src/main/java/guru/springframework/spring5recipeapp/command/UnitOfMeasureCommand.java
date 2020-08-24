package guru.springframework.spring5recipeapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Viktoriya on 11-Jun-20
 */
@NoArgsConstructor
@Getter
@Setter
public class UnitOfMeasureCommand {
	private Long id;
	private String description;
}
