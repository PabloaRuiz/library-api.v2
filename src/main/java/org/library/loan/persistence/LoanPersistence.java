package org.library.loan.persistence;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.library.loan.persistence.entity.LoanEntity;


import java.util.List;

@ApplicationScoped
public class LoanPersistence implements PanacheMongoRepositoryBase<LoanEntity, ObjectId> {

    public LoanEntity findCustomer(String customer) {
        return find("customer", customer).firstResultOptional()
                .orElseThrow(NotFoundException::new);
    }

    public List<LoanEntity> listLoans(int pageIndex, int pageSize) {
        return findAll().page(pageIndex, pageSize).list();
    }
}

