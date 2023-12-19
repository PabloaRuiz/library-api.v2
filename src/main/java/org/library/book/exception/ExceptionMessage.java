package org.library.book.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    BOOK_NOT_EXIST("There is no book with this ID");

    private final String description;
}
