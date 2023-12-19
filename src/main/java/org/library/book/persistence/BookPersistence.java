package org.library.book.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.bson.types.ObjectId;
import org.library.book.persistence.entity.BookEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
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

    public List<BookEntity> findAvaliableBooks(boolean available) {
        return list("available", available);
    }
}
