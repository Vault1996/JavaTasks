package by.epam.parsing.entity.composite.exception;

public class NotLeafException extends Exception {
    public NotLeafException() {
        super();
    }

    public NotLeafException(String message) {
        super(message);
    }

    public NotLeafException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLeafException(Throwable cause) {
        super(cause);
    }

    protected NotLeafException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
