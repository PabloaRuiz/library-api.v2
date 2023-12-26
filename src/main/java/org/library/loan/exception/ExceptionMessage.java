package org.library.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    LOAN_IS_NOT("Customer not found"),
    BOOK_NOT_AVAILABLE("Book is not available"),
    PROCESS_ERROR("Error processing list return request"),
    ERROR_CREATING("Error saving user");

    private final String description;
}
