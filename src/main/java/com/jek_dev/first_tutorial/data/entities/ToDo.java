package com.jek_dev.first_tutorial.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * a {@code Entity} which will be used to create table and store data in the MariaDB database
 */
@Getter
@Setter
@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status;

    private boolean deleted;
}
