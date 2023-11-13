package net.kcww.app.robogen.processor;

/**
 * Interface for a generic task processor.
 * This interface defines a contract for processing tasks of a specific type.
 *
 * @param <T> the type of the task to be processed.
 */
public interface TaskProcessor<T> {

    /**
     * Processes a task of type T.
     * The specific implementation of this method should define how the task is processed.
     *
     * @param t the task to be processed.
     */
    void process(T t);
}
