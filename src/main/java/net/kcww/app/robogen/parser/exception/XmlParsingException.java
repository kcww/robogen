package net.kcww.app.robogen.parser.exception;

import java.io.Serial;

/**
 * Exception class for errors occurring during XML parsing.
 */
public class XmlParsingException extends ParsingException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new XML parsing exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public XmlParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new XML parsing exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public XmlParsingException(String message) {
        super(message);
    }

    /**
     * Constructs a new XML parsing exception with no detail message.
     */
    public XmlParsingException() {
        super();
    }
}

