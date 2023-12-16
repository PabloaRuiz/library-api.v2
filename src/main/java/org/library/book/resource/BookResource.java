package org.library.book.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.library.book.domain.Book;
import org.library.book.persistence.BookPersistence;
import org.library.book.persistence.entity.BookEntity;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.library.book.persistence.converts.ConvertBook.converterToBookEntity;

@Path("api/v2/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BookResource {

    @Inject
    BookPersistence bookPersistence;

    @POST
    @ResponseStatus(201)
    public void createBook(Book book) {
        var bookDto = converterToBookEntity(book);
        bookPersistence.persist(bookDto);
    }

    @GET
    @ResponseStatus(200)
    @SneakyThrows
    public BookEntity findBookByIsbn(String isbn) {
        return Optional.of(bookPersistence.findByIsbn(isbn))
                        .orElseThrow(Exception::new);
    }

    @PUT
    @ResponseStatus(200)
    public BookEntity bookUpdate(String isbn, Book book) {
        var isbnBook = findBookByIsbn(isbn);

        isbnBook.setAuthor(book.author());
        isbnBook.setTitle(book.title());

        bookPersistence.update(isbnBook);

        return findBookByIsbn(isbn);
    }


    @GET
    @ResponseStatus(200)
    public List<BookEntity> findListBook(int page, int size) {
        return bookPersistence.findListBook(page, size);
    }

    @DELETE
    @ResponseStatus(200)
    @SneakyThrows
    public void deleteBook(ObjectId id) {
       var book = bookPersistence.findByIdOptional(id)
               .orElseThrow(Exception::new);

       bookPersistence.delete(book);
    }


}
