package by.epam.card.exception;

public class NoSuchEnumElementException extends Exception{
    public NoSuchEnumElementException() {
    }

    public NoSuchEnumElementException(String message) {
        super(message);
    }

    public NoSuchEnumElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEnumElementException(Throwable cause) {
        super(cause);
    }

    public NoSuchEnumElementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}