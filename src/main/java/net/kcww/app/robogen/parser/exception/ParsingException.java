package net.kcww.app.robogen.parser.exception;

import java.io.Serial;

public class ParsingException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new parsing exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public ParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new parsing exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new parsing exception with no detail message.
     */
    public ParsingException() {
        super();
    }
}
