package org.library.loan.resource;

import lombok.RequiredArgsConstructor;
import org.library.loan.domain.Loan;
import org.library.loan.persistence.LoanPersistence;
import org.library.loan.persistence.converts.LoanConverts;
import org.library.loan.persistence.entity.LoanEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("ap/v2/loan")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequiredArgsConstructor
public class LoanResource {

    private final LoanPersistence loanPersistence;

    @POST
    public LoanEntity createLoan(Loan loan) {
        var loanEntity = LoanConverts.convertToLoanEntity(loan);
        loanPersistence.persist(loanEntity);
        return loanEntity;
    }
}