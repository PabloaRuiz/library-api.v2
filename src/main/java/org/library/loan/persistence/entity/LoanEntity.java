package org.library.loan.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.library.book.domain.Book;

import java.time.LocalDate;

@Data
@MongoEntity(collection = "loan")
public class LoanEntity {

    @BsonId
    private ObjectId id;
    private String customer;
    private String customerEmail;
    private Book book;
    private LocalDate loanDate;
    private boolean returned;

    public LoanEntity(String customer, String customerEmail, Book book, LocalDate loanDate, boolean returned) {
        this.id = new ObjectId();
        this.customer = customer;
        this.customerEmail = customerEmail;
        this.book = book;
        this.loanDate = loanDate;
        this.returned = returned;
    }

}
