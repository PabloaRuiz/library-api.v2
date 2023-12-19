package org.library.book.domain;

public record Book(String title, String author, String isbn, int page, boolean available) {

    public static Book createBookWithoutLoans(String title, String author, String isbn, int page, boolean available) {
        return new Book(title, author, isbn, page, available);
    }
}
