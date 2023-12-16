package org.library.loan.persistence.converts;


import org.library.loan.domain.Loan;
import org.library.loan.persistence.entity.LoanEntity;

public class LoanConvert {

    public static LoanEntity convertToLoanEntity(Loan loan) {
        return new LoanEntity(
                loan.customer(),
                loan.customerEmail(),
                loan.book(),
                loan.loanDate(),
                loan.returned()
        );
    }

    public static Loan converterToLoanDomain(LoanEntity entity) {
        return new Loan(
                entity.getCustomer(),
                entity.getCustomerEmail(),
                entity.getBook(),
                entity.getLoanDate(),
                entity.isReturned());
    }

}