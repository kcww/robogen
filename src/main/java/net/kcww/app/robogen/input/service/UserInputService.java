package net.kcww.app.robogen.input.service;

import net.kcww.app.robogen.input.entity.UserInput;

import java.util.Optional;

/**
 * Interface for services handling operations related to UserInput entities.
 */
public interface UserInputService {

    /**
     * Retrieves a UserInput entity by its identifier.
     *
     * @param id The identifier of the UserInput entity.
     * @return An Optional containing the UserInput entity if found, or an empty Optional otherwise.
     */
    Optional<UserInput> get(Long id);

    /**
     * Saves a UserInput entity.
     * This can include creating a new entity or updating an existing one.
     *
     * @param entity The UserInput entity to be saved.
     * @return The saved UserInput entity.
     */
    UserInput save(UserInput entity);

    /**
     * Deletes the UserInput entity with the specified identifier.
     *
     * @param id The identifier of the UserInput entity to be deleted.
     */
    void delete(Long id);
}
