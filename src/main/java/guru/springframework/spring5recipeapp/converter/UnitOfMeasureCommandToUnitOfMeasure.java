package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 11-Jun-20
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
		if (unitOfMeasureCommand == null) {
			return null;
		}
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(unitOfMeasureCommand.getId());
		unitOfMeasure.setUom(unitOfMeasureCommand.getDescription());
		return unitOfMeasure;
	}
}
