package net.kcww.app.robogen.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ParserService<T> {
  List<T> parse(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException;

  default String stripPrefix(String value) {
    if (value != null && value.contains(":")) {
      return value.substring(value.indexOf(':') + 1);
    }
    return value;
  }
}