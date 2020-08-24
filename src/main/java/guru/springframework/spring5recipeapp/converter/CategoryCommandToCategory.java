package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 23-Aug-20
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand categoryCommand) {
		if (categoryCommand == null) {
			return null;
		}

		Category category = new Category();
		category.setId(categoryCommand.getId());
		category.setDescription(categoryCommand.getDescription());
		return category;
	}
}
