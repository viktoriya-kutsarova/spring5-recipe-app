package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.CategoryCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 16-Sep-20
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {


	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category category) {
		if (category == null) {
			return null;
		}

		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(category.getId());
		categoryCommand.setDescription(category.getDescription());
		return categoryCommand;
	}
}
