package org.library.book.domain;

import org.library.loan.domain.Loan;

import java.util.Set;

public record Book(String title, String author, String isbn, Set<Loan> loans) {

    public static Book createBookWithoutLoans(String title, String author, String isbn) {
        return new Book(title, author, isbn, Set.of());
    }
}
