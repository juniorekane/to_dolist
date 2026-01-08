package com.jek_dev.first_tutorial.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * A {@code DTO} that will be used as response object for the user
 */
@Setter
@Getter
public class ToDoDto {

    private String title;

    private String description;

    private String status;

}
