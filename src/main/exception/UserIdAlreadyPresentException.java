package com.infosys.irs.exception;

/**
 * Custom exception thrown when a userId already exists.
 *
 * Extending RuntimeException makes this an unchecked exception,
 * meaning it does not need to be declared with `throws`.
 */
public class UserIdAlreadyPresentException extends RuntimeException {

    /**
     * Used for serialization.
     * Mostly important when exceptions are transferred
     * between JVMs or stored.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that accepts an error message.
     *
     * @param message - key or description of the error
     */
    public UserIdAlreadyPresentException(String message) {
        // Calls the parent class (RuntimeException) constructor
        super(message);
    }
}
