package net.kcww.app.robogen.service;

import net.kcww.app.robogen.service.impl.exception.GherkinParsingException;
import net.kcww.app.robogen.service.impl.exception.XmlParsingException;
import net.kcww.app.robogen.service.impl.exception.XsdParsingException;

import java.io.InputStream;
import java.util.List;

public interface ParserService<T> {

  List<T> parse(InputStream inputStream) throws GherkinParsingException, XmlParsingException, XsdParsingException;
}
