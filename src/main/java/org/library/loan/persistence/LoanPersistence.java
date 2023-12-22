package org.library.loan.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.bson.types.ObjectId;
import org.library.loan.persistence.entity.LoanEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoanPersistence implements PanacheMongoRepositoryBase<LoanEntity, ObjectId> {
}

