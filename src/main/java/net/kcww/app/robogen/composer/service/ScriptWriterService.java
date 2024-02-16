package net.kcww.app.robogen.composer.service;

@FunctionalInterface
public interface ScriptWriterService<T, U, V> {

    V compose(T t, U u);
}
