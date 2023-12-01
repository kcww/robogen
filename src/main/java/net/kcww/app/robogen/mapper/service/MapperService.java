package net.kcww.app.robogen.mapper.service;

/**
 * Defines a generic contract for mapping services.
 * This interface represents a service that takes an input of type T and produces an output of type V.
 *
 * @param <T> the type of the input to the map method.
 * @param <V> the type of the result produced by the map method.
 */
@FunctionalInterface
public interface MapperService<T, V> {

    /**
     * Maps an input of type T to an output of type V.
     * Implementing classes should define the specific mapping logic.
     *
     * @param t the input object to be mapped.
     * @return the mapped object of type V.
     */
    V map(T t);
}
