package org.library.loan.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.library.book.domain.Book;
import org.library.loan.domain.Loan;
import org.library.loan.exception.ResponseError;
import org.library.loan.persistence.LoanPersistence;
import org.library.loan.persistence.converts.LoanConverts;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.library.loan.exception.ExceptionMessage.*;
import static org.library.loan.persistence.converts.LoanConverts.convertToLoanDomain;

@Path("api/v2/loan")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequiredArgsConstructor
public class LoanResource {

    private final LoanPersistence loanPersistence;

    @POST
    public Response createLoan(Loan loan) {
        var loanEntity = LoanConverts.convertToLoanEntity(loan);
        loanPersistence.persist(loanEntity);
        return Response.ok(loanEntity).build();
    }

    @GET
    @Path("/customer/{customer}")
    public Response findCustomerByLoan(String customer) {
        try {
            var loanEntity = loanPersistence.findCustomer(customer);
            return Response.ok(loanEntity).build();

        } catch (NotFoundException error) {
            return Response.status(NOT_FOUND)
                    .entity(ResponseError.builder()
                            .status(NOT_FOUND.getStatusCode())
                            .message(LOAN_IS_NOT.getDescription())
                            .build())
                    .build();
        }

    }

    @GET
    @Path("/list")
    public Response listLoan(@QueryParam("page") int page,
                             @QueryParam("size") int size) {
        try {
            var loans = loanPersistence.listLoans(page, size);
            return Response.ok(loans).build();

        } catch (Exception e) {

            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(ResponseError.builder()
                            .status(INTERNAL_SERVER_ERROR.getStatusCode())
                            .message(PROCESS_ERROR.getDescription())
                            .build())
                    .build();
        }
    }


    @PATCH
    @Path("/loan/{customer}")
    @SneakyThrows
    public Response addLoans(@PathParam("customer") String customer,
                             List<Book> loans) {
        try {
            var loanDomain = loanPersistence.findCustomer(customer);

            var loan = convertToLoanDomain(loanDomain);
            loan.addLoans(loans);
            loanDomain.setLoans(loan.loans());

            return Response.accepted()
                    .status(204)
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(ResponseError.builder()
                            .status(INTERNAL_SERVER_ERROR.getStatusCode())
                            .message(BOOK_NOT_AVAILABLE.getDescription())
                            .build())
                    .build();
        }
    }

}