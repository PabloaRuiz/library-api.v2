package org.library.book.persistence;

import org.library.book.domain.Book;

public interface BookObserver {

    void updateAvailability(Book book);
}
