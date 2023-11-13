package net.kcww.app.robogen.parser.service;

import net.kcww.app.robogen.parser.exception.ParsingException;

/**
 * Defines a generic contract for parser services.
 *
 * @param <T> the type of the input to the parse method.
 * @param <V> the type of the result produced by the parse method.
 */
public interface ParserService<T, V> {

    /**
     * Parses the given input and produces a result.
     *
     * @param t   the input to be parsed.
     * @param <E> the type of the parsing exception that might be thrown.
     * @return the parsed result of type V.
     * @throws E if a parsing error occurs.
     */
    <E extends ParsingException> V parse(T t) throws E;
}
