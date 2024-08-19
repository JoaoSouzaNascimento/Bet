package exceptions;

public class AtualizacaoException extends Exception {
    public AtualizacaoException(String message) {
        super(message);
    }

    public AtualizacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}