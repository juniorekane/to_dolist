package com.jek_dev.first_tutorial.controller;

import com.jek_dev.first_tutorial.data.entities.ToDo;
import com.jek_dev.first_tutorial.dto.ToDoDto;
import com.jek_dev.first_tutorial.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * the {@code RestController} to manage client request
 * <p>
 * This controller receives request in {@code json} format in the body and treats it as a java object
 * </p>
 */
@RestController
@AllArgsConstructor
public class Controller {

    private ToDoService toDoService;

    /**
     * The start point of communication between the user and this application.<br>
     * It recommended to create a {@link ToDo}before make a {@code GET}-Request to see all or one {@link ToDo}
     *
     * @param toDo it is not a {@link ToDo} object but a {@code DTO}, which will be convert to a {@link ToDo} later
     */
    @PostMapping("todos")
    public void createToDo(@RequestBody ToDoDto toDo) {
        toDoService.createToDo(toDo);
    }

    /**
     * The endpoint to retrieve all created {@link ToDo} stored in the database
     *
     * @return {@code List<ToDoDto>} a list that contains all created {@link ToDo}
     */
    @GetMapping("todos")
    public List<ToDoDto> getToDoListAsDto() {
        return toDoService.getAllToDos();
    }

    /**
     * the endpoint to retrieve only one {@link ToDo} by specifying an {@code id}
     *
     * @param id a unique identifier of a stored {@link ToDo} on the database
     * @return a unique {@link ToDo} as {@code DTO}
     */
    @GetMapping("todo/{titel}")
    public ToDoDto getOneToDoAsDoToDto(@PathVariable Long id) {
        return toDoService.getToDo(id);
    }

    /**
     * The endpoint to update {@link ToDo}
     *
     * @param id      a unique identifier of a stored {@link ToDo} on the database
     * @param updater contains all new element for an update
     *                this endpoint just implement an update using.
     *                <br>Related with that the {@code updater} parameter should include all element of a {@link ToDo}
     *                <br> such as a {@code title, description and status} without an id
     */
    @PutMapping("todo/{id}")
    public void updateToDo(@PathVariable Long id, @RequestBody ToDoDto updater) {
        toDoService.updateToDo(id, updater);
    }

    /**
     * This endpoint implement {@code DELETE} of HTTP-Protocol <br>
     * but is deleting nothing and just mark the column <br>
     * {@code isDeleted} of an {@link ToDo}-element as {@code true or false}
     *
     * @param id the unique identifier of the {@link ToDo}, which should be deleted
     * @return gives a {@code ResponseEntity} back with an empty body the status 2024
     */
    @DeleteMapping("todo/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}
