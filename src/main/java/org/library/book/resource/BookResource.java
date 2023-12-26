package org.library.book.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.library.book.persistence.BookPersistence;
import org.library.book.persistence.converts.ConvertBook;
import org.library.book.persistence.entity.BookEntity;
import org.library.book.domain.Book;
import org.library.loan.exception.ResponseError;


import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.library.book.exception.ExceptionMessage.*;
import static org.library.loan.exception.ExceptionMessage.ERROR_CREATING;


@Path("api/v2/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequiredArgsConstructor
public class BookResource {

    private final BookPersistence bookPersistence;

    @POST
    public Response createBook(Book book) {
        try {
            var bookEntity = ConvertBook.converterToBookEntity(book);
            bookPersistence.persist(bookEntity);
            return Response.ok(bookEntity).build();

        } catch (Exception e) {

            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(ResponseError.builder()
                            .status(INTERNAL_SERVER_ERROR.getStatusCode())
                            .message(BOOK_CREATING.getDescription())
                            .build())
                    .build();
        }
    }

    @GET
    @SneakyThrows
    @Path("/isbn/{isbn}")
    public Response findBookByIsbn(String isbn) {
        try {
            var bookEntity = bookPersistence.findByIsbn(isbn);
            return Response.ok(bookEntity).build();

        } catch (Exception e) {

            return Response.status(NOT_FOUND)
                    .entity(ResponseError.builder()
                            .status(NOT_FOUND.getStatusCode())
                            .message(BOOK_NOT_EXIST.getDescription())
                            .build())
                    .build();
        }
    }

    @GET
    @Path("/list")
    public Response findListBook(@QueryParam("page") int page,
                                 @QueryParam("size") int size) {

        try {
            var listBook = bookPersistence.listBooks(page, size);
            return Response.ok(listBook).build();

        } catch (Exception e) {

            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(ResponseError.builder()
                            .status(INTERNAL_SERVER_ERROR.getStatusCode())
                            .message(BOOK_LIST_ERROR.getDescription())
                            .build())
                    .build();
        }
    }

    @GET
    @Path("/status")
    public Response findListBook(@QueryParam("available") boolean available) {

        try {
            var listBook = bookPersistence.findAvailableBooks(available);
            return Response.ok(listBook).build();

        } catch (Exception e) {

            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(ResponseError.builder()
                            .status(INTERNAL_SERVER_ERROR.getStatusCode())
                            .message(BOOK_LIST_ERROR.getDescription())
                            .build())
                    .build();
        }
    }
}

