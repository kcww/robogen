package net.kcww.app.robogen.processor;

public interface TaskProcessor<T> {
  void process(T input);
}
