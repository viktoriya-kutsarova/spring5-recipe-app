package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 16-Sep-20
 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes notes) {
		if (notes == null) return null;

		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(notes.getId());
		notesCommand.setRecipeNotes(notes.getRecipeNotes());
		return notesCommand;
	}
}
