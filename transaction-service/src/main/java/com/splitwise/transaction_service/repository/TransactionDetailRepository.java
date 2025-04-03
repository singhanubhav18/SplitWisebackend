package com.splitwise.transaction_service.repository;

import com.splitwise.transaction_service.document.TransactionDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionDetailRepository extends MongoRepository<TransactionDetail, Integer> {
}
