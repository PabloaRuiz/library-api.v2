package org.library.loan.domain;

import lombok.SneakyThrows;
import org.library.book.domain.Book;

import java.time.Instant;
import java.util.Map;

import static org.library.loan.exception.ExceptionMessage.BOOK_NOT_AVAILABLE;

public record Loan(String customer,
                   String email,
                   Map<Book, Instant> loans
) {

    public static Loan of(String customer, String email, Map<Book, Instant> loans) {
        return new Loan(customer, email, Map.of());
    }

    @SneakyThrows
    public void addLoans(Map<Book, Instant> loan) {


        for (Map.Entry<Book, Instant> entry : loan.entrySet()) {
            var book = entry.getKey();

            var availableBook = book.available();

            if (!availableBook) {
                throw new Exception(BOOK_NOT_AVAILABLE.getDescription());
            }

            loans.put(book, entry.getValue());
        }
    }
}