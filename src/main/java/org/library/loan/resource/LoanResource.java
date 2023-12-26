package org.library.loan.resource;

import jakarta.ws.rs.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.library.book.domain.Book;
import org.library.loan.domain.Loan;
import org.library.loan.persistence.LoanPersistence;
import org.library.loan.persistence.converts.LoanConverts;
import org.library.loan.persistence.entity.LoanEntity;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.library.loan.persistence.converts.LoanConverts.convertToLoanDomain;

@Path("api/v2/loan")
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

    @GET
    @Path("/customer/{customer}")
    public LoanEntity findCustomerByLoan(String customer) {
        return loanPersistence.findCustomer(customer);
    }

    @GET
    @Path("/list")
    public List<LoanEntity> listLoan(@QueryParam("page") int page,
                                     @QueryParam("size") int size) {
        return loanPersistence.listLoans(page, size);
    }

    @PATCH
    @Path("/loan/{customer}")
    @SneakyThrows
    public void addLoans(@PathParam("customer") String customer,
                         List<Book> loans) {

        var loanDomain = loanPersistence.findCustomer(customer);

        try {
            var loan = convertToLoanDomain(loanDomain);
            loan.addLoans(loans);

            loanDomain.setLoans(loan.loans());

        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage());
        }
    }

}