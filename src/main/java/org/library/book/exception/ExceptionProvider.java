package org.library.book.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.library.loan.exception.ResponseError;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ExceptionProvider implements ExceptionMapper<MismatchedInputException> {

    @Override
    public Response toResponse(MismatchedInputException e) {
        var message = "Error in the operation, please check that the registration fields are completed";
        return Response.status(BAD_REQUEST)
                .entity(ResponseError.builder()
                        .status(BAD_REQUEST.getStatusCode())
                        .message(message)
                        .detail(e.getMessage())
                        .build())
                .build();
    }
}
