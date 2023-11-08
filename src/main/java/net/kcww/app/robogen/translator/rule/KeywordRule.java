package net.kcww.app.robogen.translator.rule;

public interface KeywordRule<T,V> {

  boolean isApplicable(T t);
  V translate(T t);
}
