package org.library.book.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
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
