package org.library.loan.domain;

import lombok.SneakyThrows;
import org.library.book.domain.Book;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record Loan(String customer,
                   String email,
                   Map<Book, Instant> loans,
                   List<Boolean> infringements) {

    public Loan(String customer, String email, Map<Book, Instant> loans, List<Boolean> infringements) {
        this.customer = customer;
        this.email = email;
        this.loans = loans;
        this.infringements = infringements;
    }


    @SneakyThrows
    public void addingInflaction(Boolean infringement) {
        infringements.add(infringement);

        if (!infringement && countInfringements() > 5) {
            throw new Exception("");
        }

    }

    public void clearIngInflaction() {
        infringements.clear();
    }


    @SneakyThrows
    public void addLoans(Map<Book, Instant> loan) {
        for (Map.Entry<Book, Instant> entry : loan.entrySet()) {
            var book = entry.getKey();

            var availableBook = book.available();

            if (!availableBook) throw new Exception("");

            loans.put(book, entry.getValue());
        }
    }

    private long countInfringements() {
        return infringements.stream()
                .filter(inflation -> !inflation)
                .count();
    }
}