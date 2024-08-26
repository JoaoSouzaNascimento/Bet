package exceptions;

import java.sql.SQLException;

public class AtualizacaoException extends SQLException {
    public AtualizacaoException(String message) {
        super(message);
    }

    public AtualizacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}