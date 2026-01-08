package com.jek_dev.first_tutorial.exception;

/**
 * An exception that will always throw, when the suchet data is not found in the database
 */
public class ToDoNotFoundException extends RuntimeException {

    /**
     * the constructor with only a message as parameter
     *
     * @param message the message why the action or transaction could not be completed
     */
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
