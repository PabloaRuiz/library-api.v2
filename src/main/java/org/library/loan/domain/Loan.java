package org.library.loan.domain;


import lombok.SneakyThrows;
import org.library.book.domain.Book;

import java.time.LocalDate;

public record Loan(String customer, String customerEmail, Book book, LocalDate loanDate, boolean returned) {


    public Loan(String customer, String customerEmail, Book book, LocalDate loanDate, boolean returned) {
        this.customer = customer;
        this.customerEmail = customerEmail;
        this.book = book;
        this.loanDate = loanDate;
        this.returned = returned;
    }

    @SneakyThrows
    public void validateBookReturnDeadline() {
        var bookReturnDay = loanDate().plusDays(10L);
        if (LocalDate.now().isAfter(bookReturnDay)) {
            throw new IllegalArgumentException("The return is out of date");
        }
    }

}