package net.kcww.app.robogen.parser.exception;

import java.io.Serial;

/**
 * Unchecked exception class for errors that occur during parser configuration.
 */
public class UncheckedParserConfigurationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new unchecked parser configuration exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public UncheckedParserConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new unchecked parser configuration exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public UncheckedParserConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructs a new unchecked parser configuration exception with no detail message.
     */
    public UncheckedParserConfigurationException() {
        super();
    }
}
