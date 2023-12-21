package org.library.book.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;


@Data
@MongoEntity(database = "Library", collection = "book")
public class BookEntity {

    @BsonId
    private ObjectId id;
    private String title;
    private String author;
    private String isbn;
    private int page;
    private boolean available;

    public BookEntity() {
        this.id = new ObjectId();
    }

    public BookEntity(String title, String author, String isbn, int page, boolean available) {
        this.id = new ObjectId();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.page = page;
        this.available = available;
    }

}