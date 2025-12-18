package com.jek_dev.first_tutorial.exception;

/**
 * An exception that will always throw, when the suchet data could not be updated in the database
 */
public class UpdateToDoException extends RuntimeException {

    /**
     * the constructor with only a message as parameter
     *
     * @param message the message why the action or transaction could not be completed
     */
    public UpdateToDoException(String message) {
        super(message);
    }
}
