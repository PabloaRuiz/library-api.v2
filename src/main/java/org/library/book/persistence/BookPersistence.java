package org.library.book.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.library.book.persistence.entity.BookEntity;

import java.util.List;

@ApplicationScoped
public class BookPersistence implements PanacheMongoRepositoryBase<BookEntity, ObjectId> {

    public List<BookEntity> findListBook(int pageIndex, int pageSize) {
        return findAll().page(pageIndex, pageSize).list();
    }

    public BookEntity findByIsbn(String isbn) {
        return find("isbn", isbn).firstResult();
    }
}
