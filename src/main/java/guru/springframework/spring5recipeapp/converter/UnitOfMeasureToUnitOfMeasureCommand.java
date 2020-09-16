package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 16-Sep-20
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	@Synchronized
	@Nullable
	public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
		if (unitOfMeasure == null) return null;

		UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
		unitOfMeasureCommand.setId(unitOfMeasure.getId());
		unitOfMeasureCommand.setUom(unitOfMeasure.getUom());
		return unitOfMeasureCommand;
	}
}
