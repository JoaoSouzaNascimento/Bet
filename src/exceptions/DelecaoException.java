package exceptions;

import java.sql.SQLException;

public class DelecaoException extends SQLException {
    public DelecaoException(String message) {
        super(message);
    }

    public DelecaoException(String message, Throwable cause) {
        super(message, cause);
    }
}