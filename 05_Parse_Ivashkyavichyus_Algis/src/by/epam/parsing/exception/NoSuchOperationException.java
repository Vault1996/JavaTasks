package by.epam.parsing.exception;

public class NoSuchOperationException extends Exception {
	public NoSuchOperationException() {
		super();
	}

	public NoSuchOperationException(String message) {
		super(message);
	}

	public NoSuchOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchOperationException(Throwable cause) {
		super(cause);
	}

	protected NoSuchOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
