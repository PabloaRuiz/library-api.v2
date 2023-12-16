package org.library.loan.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.library.loan.persistence.entity.LoanEntity;

@ApplicationScoped
public class LoanPersistence implements PanacheMongoRepositoryBase<LoanEntity, ObjectId> {
}
