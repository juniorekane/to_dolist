package com.jek_dev.first_tutorial.handler;

import com.jek_dev.first_tutorial.exception.ToDoNotFoundException;
import com.jek_dev.first_tutorial.exception.UpdateToDoException;
import com.jek_dev.first_tutorial.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * An error handler to communicate the user what the error was
 */
@RestControllerAdvice
public class GlobalControllerHttpResponsehandler {

    /**
     * Catch all {@link ToDoNotFoundException} and send his to the user with an error status code
     *
     * @param exception the {@link ToDoNotFoundException} that was sendet from the {@link ToDoService}
     * @return a {@code ResponseEntity} with a {@code HTTP} status code and the exception message in his body
     */
    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ToDoNotFoundException exception) {
        HashMap<String, String> body = new HashMap<>();

        body.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Catch all {@link UpdateToDoException} and send his to the user with an error status code
     *
     * @param exception the {@link UpdateToDoException} that was sendet from the {@link ToDoService}
     * @return a {@code ResponseEntity} with a {@code HTTP} status code and the exception message in his body
     */
    @ExceptionHandler(UpdateToDoException.class)
    public ResponseEntity<Map<String, String>> handleUpdateError(UpdateToDoException exception) {
        HashMap<String, String> body = new HashMap<>();

        body.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(body);
    }
}
