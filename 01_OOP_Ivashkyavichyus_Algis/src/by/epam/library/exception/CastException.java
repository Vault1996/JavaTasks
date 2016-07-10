package by.epam.library.exception;

public class CastException extends Exception{
    public CastException() {
    }

    public CastException(String message) {
        super(message);
    }

    public CastException(String message, Throwable cause) {
        super(message, cause);
    }

    public CastException(Throwable cause) {
        super(cause);
    }

    public CastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
