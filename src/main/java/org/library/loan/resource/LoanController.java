package org.library.loan.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.library.loan.persistence.LoanPersistence;
import org.library.loan.persistence.entity.LoanEntity;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("api/v2/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class LoanController {

    @Inject
    LoanPersistence persistence;

    @POST
    @ResponseStatus(201)
    public LoanEntity postTest() {
        return null;
    }
}
