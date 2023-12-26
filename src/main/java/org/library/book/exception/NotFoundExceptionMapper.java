package org.library.book.exception;



import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import static org.library.book.exception.ExceptionMessage.BOOK_NOT_EXIST;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    public Response toResponse(NotFoundException exception) {
        return Response.status(NOT_FOUND)
                .entity(ResponseError.builder()
                        .status(NOT_FOUND.getStatusCode())
                        .message(BOOK_NOT_EXIST.getDescription())
                        .detail(exception.getMessage())
                        .build())
                .build();
    }
}
