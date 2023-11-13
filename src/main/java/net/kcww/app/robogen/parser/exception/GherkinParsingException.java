package net.kcww.app.robogen.parser.exception;

import java.io.Serial;

/**
 * Exception class for errors occurring during Gherkin parsing.
 */
public class GherkinParsingException extends ParsingException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new Gherkin parsing exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public GherkinParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new Gherkin parsing exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public GherkinParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new Gherkin parsing exception with no detail message.
     */
    public GherkinParsingException() {
        super();
    }
}
