package com.jek_dev.first_tutorial.service;


import com.jek_dev.first_tutorial.base.Status;
import com.jek_dev.first_tutorial.data.entities.ToDo;
import com.jek_dev.first_tutorial.data.repository.ToDoRepository;
import com.jek_dev.first_tutorial.dto.ToDoDto;
import com.jek_dev.first_tutorial.exception.ToDoNotFoundException;
import com.jek_dev.first_tutorial.exception.UpdateToDoException;
import com.jek_dev.first_tutorial.mapper.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing ToDo entities.
 * <p>
 * Provides methods to create, retrieve, update, and delete ToDo items.
 * Uses {@link ToDoRepository} for persistence and {@link Mapper} for DTO conversions.
 * </p>
 *
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ToDoNotFoundException} - thrown when a ToDo cannot be found.</li>
 *   <li>{@link UpdateToDoException} - thrown when update data is incomplete or invalid.</li>
 * </ul>
 * <p>
 * Annotations:
 * <ul>
 *   <li>{@code @Service} - marks this class as a Spring service component.</li>
 *   <li>{@code @Slf4j} - enables logging via Lombok.</li>
 *   <li>{@code @AllArgsConstructor} - generates a constructor with required arguments.</li>
 * </ul>
 */

@Slf4j
@Service
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    private final Mapper mapper;

    /**
     * Creates a new ToDo entry.
     *
     * @param toDo the DTO containing ToDo details
     */
    public void createToDo(ToDoDto toDo) {
        if (toDo.getTitle() == null)
            throw new ToDoNotFoundException("Bitte Titel setzen");

        if (toDo.getDescription() == null)
            throw new ToDoNotFoundException("Bitte Beschreibung setzen");

        toDoRepository.save(mapper.mapToToDo(toDo));
    }

    /**
     * Retrieves all ToDo entries.
     *
     * @return a list of {@link ToDoDto} objects
     * @throws ToDoNotFoundException if no ToDos are found
     */
    public List<ToDoDto> getAllToDos() {
        List<ToDoDto> dtoList = toDoRepository.findAll().stream().filter(toDo -> !toDo.isDeleted()).map(mapper::mapToToDoDto).toList();

        if (dtoList.isEmpty()) {
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Es sind aktuell keine TODOs vorhanden");
        }

        return dtoList;
    }

    /**
     * Retrieves a single ToDo by its ID.
     *
     * @param id the ID of the ToDo
     * @return the corresponding {@link ToDoDto}
     * @throws ToDoNotFoundException if the ToDo does not exist
     */
    public ToDoDto getToDo(Long id) {
        ToDo toDo;
        toDo = toDoRepository.findById(id).orElse(null);
        if (toDo == null) {
            log.debug("Fehler beim Abrufen vom TODO.\n Die ID {} existiert nicht", id);
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Die ID " + id + " existiert nicht");
        }

        if (toDo.isDeleted())
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Die ID " + id + " existiert nicht");
        ToDoDto dto = mapper.mapToToDoDto(toDo);

        if (dto == null) {
            log.debug("Fehler die ");
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Die ID " + id + " existiert nicht");
        }
        return dto;
    }

    /**
     * Updates an existing ToDo entry.
     *
     * @param id     the ID of the ToDo to update
     * @param update the DTO containing updated ToDo details
     * @throws ToDoNotFoundException if the ToDo does not exist or is marked as deleted
     * @throws UpdateToDoException   if required fields (title, description, status) are missing
     */
    @Transactional
    public void updateToDo(Long id, ToDoDto update) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if (toDo == null) {
            log.debug("Die ID {} existiert nicht", id);
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Die ID " + id + " existiert nicht");
        }

        if (toDo.isDeleted())
            throw new ToDoNotFoundException("Fehler beim Abrufen vom TODO. Die ID " + id + " existiert nicht");

        if (update.getTitle() == null)
            throw new UpdateToDoException("Zum Updaten muss ein vollständiges ToDo gegeben werden. Aktuell fehlt ein Titel");
        toDo.setTitle(update.getTitle());

        if (update.getDescription() == null)
            throw new UpdateToDoException("Zum Updaten muss ein vollständiges ToDo gegeben werden. Aktuell fehlt eine Beschreibung");
        toDo.setDescription(update.getDescription());

        if (update.getStatus() == null)
            throw new UpdateToDoException("Zum Updaten muss ein vollständiges ToDo gegeben werden. Aktuell fehlt einen Status");
        toDo.setStatus((Status.UPDATED).toString());

        toDoRepository.save(toDo);
    }

    /**
     * Marks a ToDo as deleted.
     *
     * @param id the ID of the ToDo to delete
     * @throws ToDoNotFoundException if the ToDo does not exist
     */
    @Transactional
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);

        if (toDo == null) {
            log.debug("Fehler ID {} nicht gefunden", id);
            throw new ToDoNotFoundException("Für die ID (" + id + ") existiert keine gespeicherte ToDo.");
        }
        toDo.setDeleted(true);
        toDoRepository.save(toDo);
    }

}
