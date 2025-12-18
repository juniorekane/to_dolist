package com.jek_dev.first_tutorial.mapper;

import com.jek_dev.first_tutorial.base.Status;
import com.jek_dev.first_tutorial.data.entities.ToDo;
import com.jek_dev.first_tutorial.dto.ToDoDto;
import org.springframework.stereotype.Service;


/**
 * A mapper to convert {@link ToDo} in {@link ToDoDto} and the reverse case
 */
@Service
public class Mapper {

    /**
     * This method allow user by creating a {@link ToDo} to convert the received {@code DTO} into a {@link ToDo}
     *
     * @param toDo a {@code DTO} that will be converted to a {@link ToDo}
     * @return a converted {@link ToDo} object
     */
    public ToDo mapToToDo(ToDoDto toDo) {
        ToDo mappedToDo = new ToDo();

        mappedToDo.setTitle(toDo.getTitle());
        mappedToDo.setDescription(toDo.getDescription());
        mappedToDo.setStatus((Status.OPEN).toString());
        mappedToDo.setDeleted(false);
        return mappedToDo;
    }

    /**
     * This method allow user by getting a {@link ToDo} to convert the received {@code DTO} into a {@link ToDo}
     *
     * @param toDo a {@code ToDo} that will be converted to a {@link ToDoDto}
     * @return
     */
    public ToDoDto mapToToDoDto(ToDo toDo) {
        ToDoDto dto = new ToDoDto();
        dto.setTitle(toDo.getTitle());
        dto.setDescription(toDo.getDescription());
        dto.setStatus(toDo.getStatus());
        return dto;

    }
}
