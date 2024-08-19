package exceptions;

public class DelecaoException extends Exception {
    public DelecaoException(String message) {
        super(message);
    }

    public DelecaoException(String message, Throwable cause) {
        super(message, cause);
    }
}