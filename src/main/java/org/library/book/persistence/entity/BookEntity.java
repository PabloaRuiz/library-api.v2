package org.library.book.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.library.loan.domain.Loan;

import java.util.Set;


@Data
@MongoEntity(collection = "book")
public class BookEntity {

    @BsonId
    private ObjectId id;
    private String title;
    private String author;
    private String isbn;
    private Set<Loan> loans;

    public BookEntity(String title, String author, String isbn, Set<Loan> loans) {
        this.id = new ObjectId();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.loans = loans;
    }

    public BookEntity(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.loans = null;
    }

}