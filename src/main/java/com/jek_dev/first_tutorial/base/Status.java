package com.jek_dev.first_tutorial.base;

import com.jek_dev.first_tutorial.data.entities.ToDo;

/**
 * Base enumeration class to define status of all {@link ToDo}
 * <p>
 * {@code UPDATE} is the default status by any changes in one {@link ToDo}<br>
 * {@code OPEN} is the default status of any created {@link ToDo}<br>
 * {@code DONE} maybe the status of a completed @link{@link ToDo}
 * </p>
 */
public enum Status {
    UPDATED, OPEN, DONE
}
