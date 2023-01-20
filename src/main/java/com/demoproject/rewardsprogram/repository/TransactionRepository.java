package com.demoproject.rewardsprogram.repository;

import com.demoproject.rewardsprogram.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate start, LocalDate end);
    List<Transaction> findByCustomerId(String customerId);
}