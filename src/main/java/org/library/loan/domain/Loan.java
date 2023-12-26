package org.library.loan.domain;

import lombok.SneakyThrows;
import org.library.book.domain.Book;
import org.library.book.persistence.BookObserver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.library.loan.exception.ExceptionMessage.BOOK_NOT_AVAILABLE;

public record Loan(String customer,
                   String email,
                   List<Book> loans
) {

    private static final List<BookObserver> observers =
            new ArrayList<>();

    public void addObserver(BookObserver observer) {
        observers.add(observer);

        for (Book book : loans) {
            observer.updateAvailability(book);
        }
    }

    public static Loan of(String customer, String email, List<Book> loans) {
        return new Loan(customer, email, List.of());
    }

    @SneakyThrows
    public void addLoans(List<Book> books) {

        for (Book book : books) {
            var availableBook = book.available();

            if (!availableBook) {
                throw new Exception(BOOK_NOT_AVAILABLE.getDescription());
            }

            loans().add(book);

            addObserver(book);
        }
    }
}