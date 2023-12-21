package org.library.loan.persistence.converts;

import org.library.loan.domain.Loan;
import org.library.loan.persistence.entity.LoanEntity;

public class LoanConverts {

    public static LoanEntity convertToLoanEntity(Loan loan) {
        return new LoanEntity(
                loan.customer(),
                loan.email(),
                loan.loans());
    }

    public static Loan convertToLoanDomain(LoanEntity entity) {
        return new Loan(
                entity.getCustomer(),
                entity.getEmail(),
                entity.getLoans());
    }
}
