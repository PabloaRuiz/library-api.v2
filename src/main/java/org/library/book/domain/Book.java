package org.library.book.domain;

import org.library.book.persistence.BookObserver;
import org.library.book.persistence.BookPersistence;

public record Book(String title,
                   String author,
                   String isbn,
                   int page,
                   boolean available) implements BookObserver {

    private static final BookPersistence bookPersistence = new BookPersistence();

    public static Book of(String title, String author, String isbn, int page, boolean available) {
        return new Book(title, author, isbn, page, available);
    }

    @Override
    public void updateAvailability(Book book) {
        var bookEntity = bookPersistence.findByIsbn(book.isbn);
        if (bookEntity.isAvailable()) {
          bookPersistence.leaveUnavailable(book.isbn);
        }
    }
}
