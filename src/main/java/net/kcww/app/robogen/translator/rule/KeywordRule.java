package net.kcww.app.robogen.translator.rule;

/**
 * Interface defining a contract for keyword rules.
 * This interface represents rules that can check for applicability and perform translation
 * from an input of type T to an output of type V.
 *
 * @param <T> the type of the input to the rule.
 * @param <V> the type of the output produced by the rule.
 */
public interface KeywordRule<T, V> {

    /**
     * Determines if the rule is applicable to the given input.
     *
     * @param t the input object to be checked for applicability.
     * @return true if the rule is applicable; false otherwise.
     */
    boolean isApplicable(T t);

    /**
     * Translates the given input into an output of type V.
     * This method should be implemented to define the specific translation logic.
     *
     * @param t the input object to be translated.
     * @return the translated object of type V.
     */
    V translate(T t);
}
