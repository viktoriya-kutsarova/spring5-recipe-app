package guru.springframework.spring5recipeapp.command;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Viktoriya on 23-Aug-20
 */
@NoArgsConstructor
@Getter
@Setter
public class IngredientCommand {
	private Long id;
	private Long recipeId;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeasure;
}
