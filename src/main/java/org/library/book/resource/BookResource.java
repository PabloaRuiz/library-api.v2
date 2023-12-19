package org.library.book.resource;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.library.book.domain.Book;
import org.library.book.persistence.BookPersistence;
import org.library.book.persistence.entity.BookEntity;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.library.book.persistence.converts.ConvertBook.converterToBookEntity;

@Path("api/v2/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RequiredArgsConstructor
public class BookResource {

    private final BookPersistence bookPersistence;

    @POST
    public BookEntity createBook(Book book) {
        var bookDto = converterToBookEntity(book);
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
