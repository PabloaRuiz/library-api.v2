package org.library.loan.persistence.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.library.book.domain.Book;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@MongoEntity(database = "Library", collection = "loan")
public class LoanEntity {

    @BsonId
    private ObjectId id;
    private String customer;
    private String email;
    private Map<Book, Instant> loans;
    private List<Boolean> infringements;

    public LoanEntity() {
        this.id = new ObjectId();
    }

    public LoanEntity(ObjectId id, String customer, String email, Map<Book, Instant> loans, List<Boolean> infringements) {
        this.id = new ObjectId();
        this.customer = customer;
        this.email = email;
        this.loans = loans;
        this.infringements = infringements;
    }


}
