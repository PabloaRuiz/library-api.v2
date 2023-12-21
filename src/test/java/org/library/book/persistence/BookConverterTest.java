package org.library.book.persistence;


import org.junit.jupiter.api.Test;
import org.library.book.domain.Book;
import org.library.book.persistence.entity.BookEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.library.book.persistence.converts.ConvertBook.converterToBookDomain;
import static org.library.book.persistence.converts.ConvertBook.converterToBookEntity;


public class BookConverterTest {

    @Test
    void returnEntityBookToDomainBook() {
        var book = Book.of(
                "Teste unitários",
                "Desconhecido",
                "15411548",
                120,
                false
                );

        var entityBook = converterToBookEntity(book);

        assertEquals(entityBook.getTitle(), book.title());
        assertEquals(entityBook.getAuthor(), book.author());
        assertEquals(entityBook.getIsbn(), book.isbn());
        assertEquals(entityBook.getPage(), book.page());
        assertEquals(entityBook.isAvailable(), book.available());

    }

    @Test
    void returnDomainBookToEntityBook() {
        var bookEntity = new BookEntity(
                "Teste unitários",
                "Desconhecido",
                "15411548",
                150,
                true
        );

        var book = converterToBookDomain(bookEntity);

        assertEquals(book.title(), bookEntity.getTitle());
        assertEquals(book.author(), bookEntity.getAuthor());
        assertEquals(book.isbn(), bookEntity.getIsbn());
        assertEquals(book.page(), bookEntity.getPage());
        assertEquals(book.available(), bookEntity.isAvailable());

    }


}