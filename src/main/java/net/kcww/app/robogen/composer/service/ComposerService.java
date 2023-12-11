package net.kcww.app.robogen.composer.service;

/**
 * Functional interface for composing type V objects from type T objects.
 *
 * @param <T> the type of the input to the compose method
 * @param <V> the type of the result of the compose method
 */
@FunctionalInterface
public interface ComposerService<T, V> {

    /**
     * Compose an object of type V from an object of type T.
     *
     * @param input the input object of type T
     * @return the composed object of type V
     */
    V compose(T input);
}
