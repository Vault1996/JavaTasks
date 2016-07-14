package by.epam.parsing.exception;

public class CantInterpretException extends Exception {

	public CantInterpretException() {
		super();
	}

	public CantInterpretException(String message) {
		super(message);
	}

	public CantInterpretException(String message, Throwable cause) {
		super(message, cause);
	}

	public CantInterpretException(Throwable cause) {
		super(cause);
	}

	protected CantInterpretException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
