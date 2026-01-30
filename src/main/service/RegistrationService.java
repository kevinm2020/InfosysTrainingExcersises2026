package com.infosys.irs.service;

// Spring imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Importing from other packages
import com.infosys.irs.entity.UserEntity;     // Database Entity (maps to a table)
import com.infosys.irs.model.User;            // Model (used by controller / UI)
import com.infosys.irs.repository.UserRepository; // Repository (DB operations)
import com.infosys.irs.exception.UserIdAlreadyExistsException;

/**
 * @Service marks this class as a service-layer component.
 *
 * The service layer:
 * - Contains business logic
 * - Talks to repositories
 * - Is called by controllers
 */
@Service
public class RegistrationService {

    /**
     * UserRepository is injected by Spring.
     * 
     * The repository handles all database interactions
     * (save, find, exists, etc.).
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     *
     * @param user - model object received from controller
     * @throws UserIdAlreadyExistsException if userId already exists
     */
    public void registerUser(User user) throws UserIdAlreadyExistsException {

        /**
         * Check if a user with the given userId already exists in the DB.
         * existsById() is provided by JpaRepository.
         */
        boolean userExists = userRepository.existsById(user.getUserId());

        // If user already exists, throw a custom exception
        if (userExists) {
            throw new UserIdAlreadyExistsException(
                    "RegistrationService.USERID_PRESENT"
            );
        }

        /**
         * Map Model -> Entity
         *
         * Model (User):
         * - Used for UI / form binding
         *
         * Entity (UserEntity):
         * - Used for database persistence
         */
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(user.getUserId());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setCity(user.getCity());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());

        /**
         * Save entity to the database.
         *
         * saveAndFlush():
         * - save() persists the entity
         * - flush() forces the SQL execution immediately
         */
        userRepository.saveAndFlush(userEntity);
    }
}
