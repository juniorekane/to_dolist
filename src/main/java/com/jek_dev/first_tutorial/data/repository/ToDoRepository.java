package com.jek_dev.first_tutorial.data.repository;

import com.jek_dev.first_tutorial.data.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface, which implement {@link JpaRepository} and help to create SQL request without<br>
 * writing any query by your self
 */
@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
