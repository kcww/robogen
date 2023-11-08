package net.kcww.app.robogen.translator.service;

import java.util.List;

public interface TranslatorService<T, V> {

  V translate(T model);
  List<V> translate(List<T> models);
}
