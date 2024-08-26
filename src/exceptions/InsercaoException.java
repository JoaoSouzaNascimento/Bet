package exceptions;

import java.sql.SQLException;

public class InsercaoException extends SQLException {
    public InsercaoException(String message) {
        super(message);
    }

    public InsercaoException(String message, Throwable cause) {
        super(message, cause);
    }
}