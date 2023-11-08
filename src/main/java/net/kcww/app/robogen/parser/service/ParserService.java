package net.kcww.app.robogen.parser.service;

import net.kcww.app.robogen.parser.exception.ParsingException;

import java.io.InputStream;

public interface ParserService<T> {

  <V extends ParsingException> T parse(InputStream inputStream) throws V;
}
