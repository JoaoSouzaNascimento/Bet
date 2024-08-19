package exceptions;

public class InsercaoException extends Exception {
    public InsercaoException(String message) {
        super(message);
    }

    public InsercaoException(String message, Throwable cause) {
        super(message, cause);
    }
}