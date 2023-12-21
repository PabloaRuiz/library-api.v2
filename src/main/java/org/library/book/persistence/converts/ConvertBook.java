package org.library.book.persistence.converts;

import org.library.book.persistence.entity.BookEntity;
import org.library.book.domain.Book;

public class ConvertBook {

    public static BookEntity converterToBookEntity(Book domain) {
        return new BookEntity(
                domain.title(),
                domain.author(),
                domain.isbn(),
                domain.page(),
                domain.available());
    }

    public static Book converterToBookDomain(BookEntity entity) {
        return new Book(
                entity.getTitle(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getPage(),
                entity.isAvailable());
    }
}
