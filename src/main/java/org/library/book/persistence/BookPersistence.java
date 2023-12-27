package org.library.book.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.library.book.persistence.entity.BookEntity;

import java.util.List;

@ApplicationScoped
public class BookPersistence implements PanacheMongoRepositoryBase<BookEntity, ObjectId> {


    public List<BookEntity> listBooks(int pageIndex, int pageSize) {
        return findAll().page(pageIndex, pageSize).list();
    }

    public BookEntity findByIsbn(String isbn) {
        return find("isbn", isbn).firstResultOptional()
                .orElseThrow(NotFoundException::new);
    }

    public void leaveUnavailable(String isbn) {
        var book = findByIsbn(isbn);
        book.setAvailable(false);
        persistOrUpdate(book);
    }

    public List<BookEntity> findAvailableBooks(boolean available) {
        return list("available", available);
    }


}
