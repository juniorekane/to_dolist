package com.jek_dev.first_tutorial.service;

import com.jek_dev.first_tutorial.data.entities.ToDo;
import com.jek_dev.first_tutorial.data.repository.ToDoRepository;
import com.jek_dev.first_tutorial.dto.ToDoDto;
import com.jek_dev.first_tutorial.exception.ToDoNotFoundException;
import com.jek_dev.first_tutorial.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @Mock
    private ToDoRepository mockToDoRepository;
    @Mock
    private Mapper mapper;

    private ToDoDto toDoDto;

    @BeforeEach
    void setUp(){
        toDoDto = new ToDoDto();
        toDoDto.setTitle("test");
        toDoDto.setDescription("test");

        mapper = new Mapper();

    }

    @Test
    void createToDoSuccess() {

        ToDoService testService = new ToDoService(mockToDoRepository, mapper);

        testService.createToDo(toDoDto);

        verify(mockToDoRepository).save(any(ToDo.class));
    }

    @Test
    void createToDoFailedBecauseMissingTitle(){
        ToDoService testService = new ToDoService(mockToDoRepository, mapper);

        toDoDto.setTitle(null);

        ToDoNotFoundException ex = assertThrows(ToDoNotFoundException.class, () -> testService.createToDo(toDoDto));

        assertNotNull(ex.getMessage());
        assertEquals("Bitte Titel setzen", ex.getMessage());
        verify(mockToDoRepository, times(0)).save(any(ToDo.class));
    }

    @Test
    void createToDoFailedBecauseMissingDescription(){
        ToDoService testService = new ToDoService(mockToDoRepository, mapper);

        toDoDto.setDescription(null);

        ToDoNotFoundException ex = assertThrows(ToDoNotFoundException.class, () -> testService.createToDo(toDoDto));

        assertNotNull(ex.getMessage());
        assertEquals("Bitte Beschreibung setzen", ex.getMessage());
        verify(mockToDoRepository, times(0)).save(any(ToDo.class));
    }

    @Test
    void getAllToDosSuccess() {
        ToDo todo = new ToDo();
        List<ToDo> toDoList = List.of(todo);
        when(mockToDoRepository.findAll()).thenReturn(toDoList);

        ToDoService testService = new ToDoService(mockToDoRepository, mapper);

        List<ToDoDto> dtoList = testService.getAllToDos();

        assertNotNull(dtoList);
        assertNull(dtoList.getFirst().getTitle());
        assertNull(dtoList.getFirst().getDescription());
        assertEquals(toDoList.getFirst().getStatus(), dtoList.getFirst().getTitle());
        assertEquals(toDoList.getFirst().getDescription(), dtoList.getFirst().getDescription());
        verify(mockToDoRepository).findAll();
    }

    @Test
    void getAllToDosFailedBecauseEmptyList(){
        ToDo todo = new ToDo();
        List<ToDo> toDoList = List.of(todo);
        when(mockToDoRepository.findAll()).thenReturn(Collections.emptyList());

        ToDoService testService = new ToDoService(mockToDoRepository, mapper);

        ToDoNotFoundException ex = assertThrows(ToDoNotFoundException.class, testService::getAllToDos);

        assertEquals("Fehler beim Abrufen vom TODO. Es sind aktuell keine TODOs vorhanden", ex.getMessage());
        verify(mockToDoRepository).findAll();

    }

    @Test
    void getToDoSuccess() {
    }

    @Test
    void updateToDo() {
    }

    @Test
    void deleteToDo() {
    }
}