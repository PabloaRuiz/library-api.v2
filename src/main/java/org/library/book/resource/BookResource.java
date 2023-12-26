package org.library.book.resource;


import jakarta.ws.rs.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.library.book.persistence.BookPersistence;
import org.library.book.persistence.converts.ConvertBook;
import org.library.book.persistence.entity.BookEntity;
import org.library.book.domain.Book;


import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("api/v2/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequiredArgsConstructor
public class BookResource {

    private final BookPersistence bookPersistence;

    @POST
    public BookEntity createBook(Book book) {
        var bookDto = ConvertBook.converterToBookEntity(book);
        bookPersistence.persist(bookDto);
        return bookDto;
    }

    @GET
    @SneakyThrows
    @Path("/isbn/{isbn}")
    public BookEntity findBookByIsbn(String isbn) {
        return bookPersistence.findByIsbn(isbn);

    }

    @GET
    @Path("/list")
    public List<BookEntity> findListBook(@QueryParam("page") int page,
                                         @QueryParam("size") int size) {
        return bookPersistence.listBooks(page, size);
    }

    @GET
    @Path("/status")
    public List<BookEntity> findListBook(@QueryParam("available") boolean available) {
        return bookPersistence.findAvaliableBooks(available);
    }

}
