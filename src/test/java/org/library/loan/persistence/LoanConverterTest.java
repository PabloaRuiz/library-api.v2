package org.library.loan.persistence;

import org.junit.jupiter.api.Test;
import org.library.book.domain.Book;
import org.library.loan.domain.Loan;
import org.library.loan.persistence.entity.LoanEntity;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.library.loan.persistence.converts.LoanConverts.convertToLoanDomain;
import static org.library.loan.persistence.converts.LoanConverts.convertToLoanEntity;

public class LoanConverterTest {

    @Test
    void returnEntityLoanToDomainLoan() {
        var loan = Loan.of(
                "Pablo",
                "Pablo@gmail.com",
                Map.of(Book.of(
                        "Teste unitários",
                        "Desconhecido",
                        "15411548",
                        120,
                        false
                ), Instant.now())
        );

        var entityLoan = convertToLoanEntity(loan);

        assertEquals(entityLoan.getCustomer(), loan.customer());
        assertEquals(entityLoan.getEmail(), loan.email());
        assertEquals(entityLoan.getLoans(), loan.loans());
    }

    @Test
    void returnDomainLoanToEntityLoan() {
        var loanentity = new LoanEntity(
                "Pablo",
                "Pablo@gmail.com",
                Map.of(Book.of("Teste unitários",
                        "Desconhecido",
                        "15411548",
                        120,
                        false
                ),Instant.now()));

        var loan = convertToLoanDomain(loanentity);

        assertEquals(loan.customer(), loanentity.getCustomer());
        assertEquals(loan.email(), loanentity.getEmail());
        assertEquals(loan.loans(), loanentity.getLoans());
    }
}
