package net.kcww.app.robogen.parser.exception;

import java.io.Serial;

/**
 * Exception class for errors occurring during XSD parsing.
 */
public class XsdParsingException extends ParsingException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new XSD parsing exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public XsdParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new XSD parsing exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public XsdParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new XSD parsing exception with no detail message.
     */
    public XsdParsingException() {
        super();
    }
}

