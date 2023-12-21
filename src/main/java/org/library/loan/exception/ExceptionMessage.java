package org.library.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    INFRACTION_LIMIT("You have reached the maximum infraction limit"),
    BOOK_NOT_AVAILABLE("book is not available");

    private final String description;
}
