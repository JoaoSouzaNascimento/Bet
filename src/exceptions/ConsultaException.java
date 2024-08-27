package exceptions;

import java.sql.SQLException;

public class ConsultaException extends Exception {
    public ConsultaException(String message) {
        super(message);
    }

    public ConsultaException(String message, Throwable cause) {
        super(message, cause);
    }
}

