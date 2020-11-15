package guru.springframework.spring5recipeapp.exceptions;

/**
 * Created by Viktoriya on 15-Nov-20
 */
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
