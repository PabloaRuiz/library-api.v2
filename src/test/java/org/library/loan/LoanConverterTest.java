package org.library.loan;

import org.junit.jupiter.api.Test;
import org.library.book.domain.Book;
import org.library.loan.domain.Loan;
import org.library.loan.persistence.entity.LoanEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.library.loan.persistence.converts.LoanConvert.convertToLoanEntity;
import static org.library.loan.persistence.converts.LoanConvert.converterToLoanDomain;

public class LoanConverterTest {

    @Test
    void returnEntityLoanToLoan() {
        var loan = new Loan(
                "Pablo",
                "Pablo@gmail.com",
                createBook(),
                LocalDate.now(),
                true
        );

        var entityLoan = convertToLoanEntity(loan);

        assertEquals(entityLoan.getCustomer(), loan.customer());
        assertEquals(entityLoan.getCustomerEmail(), loan.customerEmail());
        assertEquals(entityLoan.getBook(), loan.book());
        assertEquals(entityLoan.getLoanDate(), loan.loanDate());
        assertEquals(entityLoan.isReturned(), loan.returned());

    }

    @Test
    void returnDomainLoanToEntityLoan() {
        var loanEntity = new LoanEntity(
                "Pablo",
                "Pablo@gmail.com",
                createBook(),
                LocalDate.now(),
                true
        );

        var loan = converterToLoanDomain(loanEntity);

        assertEquals(loan.customer(), loanEntity.getCustomer());
        assertEquals(loan.customerEmail(), loanEntity.getCustomerEmail());
        assertEquals(loan.book(), loanEntity.getBook());
        assertEquals(loan.loanDate(), loanEntity.getLoanDate());
        assertEquals(loan.returned(), loanEntity.isReturned());

    }

    private Book createBook() {
        return Book.createBookWithoutLoans(
                "Teste unitários",
                "Desconhecido",
                "15411548"
        );
    }

}