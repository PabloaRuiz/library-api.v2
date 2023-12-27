package org.library.book.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    BOOK_NOT_EXIST("There is no book with this ID"),
    BOOK_NOT_IS_ON_LOAN("The selected book is on loan to another customer"),
    BOOK_CREATING("Error saving book"),
    BOOK_LIST_ERROR("error returning list");


    private final String description;
}
