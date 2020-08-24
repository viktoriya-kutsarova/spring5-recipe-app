package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 23-Aug-20
 */

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand notesCommand) {
		if (notesCommand == null) {
			return null;
		}

		Notes notes = new Notes();
		notes.setId(notesCommand.getId());
		notes.setRecipeNotes(notesCommand.getRecipeNotes());
		return notes;
	}
}
