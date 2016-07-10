package by.epam.library.exception;

public class NoSuchNameException extends Exception {
    public NoSuchNameException() {
    }

    public NoSuchNameException(String message) {
        super(message);
    }

    public NoSuchNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNameException(Throwable cause) {
        super(cause);
    }

    public NoSuchNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
